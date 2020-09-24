# stream-rabbitMQ 发送方
   ## 流程说明
   ```
    消息生产者发送消息，进入到 【SpringCloud-Stream】 组建中来，发送时：
        1、将Source消息数据，通过指定的 Channel，发送到指定的通道上
        2、由于 channel 和 bander（配置的mq信息，例如：rabbit、kafka） 使用配置进行绑定了
        3、就可以根据 bander 和 channel 的信息发送到MQ的服务器上（rabbitMq、Kafka）
   ```
   ## 配置示例及说明
   ```
    spring:
      cloud:
        stream:
          binders: # 在此处配置要绑定的rabbitmq的服务信息；
            defaultRabbit: # 表示定义的名称，用于于binding整合
              type: rabbit # 消息组件类型
              environment: # 设置rabbitmq的相关的环境配置
                spring:
                  rabbitmq:
                    host: 120.79.136.105
                    port: 5672
                    username: guest
                    password: guest

          bindings: # 服务的整合处理
            output: # output：发送者， input：消费者   【这个名字是一个通道的名称】
              destination: studyExchange  # 表示要使用的Exchange名称定义　【相当于声明一个交换机】
              content-type: application/json  # 设置消息类型，本次为json，文本则设置“text/plain”
              bander: defaultRabbit # 设置要绑定的消息服务的具体设置，对应上面的 binders
    
          rabbit:
            bindings:
              output:
                producer:
                  routing-key-expression: headers.type  # 发送的消息根据什么路由
                  exchangeType: direct  # 声明交换机的类型

   ```
   ```
    说明：
        个人理解该配置，大概分为三部分，binders、bindings、rabbit（可能kafka不一样，没用过）
        binders：
            主要定义MQ服务器环境相关信息，具体到了某一种MQ的实现，看配置应该可以定义多个
            1、binders.defaultRabbit:  
                这个 defaultRabbit 就是定义了一个MQ信息的配置，这个名称可自行定义，后续其他地方引用即可
    
        bindings：
            主要是定义Channel，【消息的发送都是通过Channel的】
            1、bindings.output:
                这个 【output】名称就是 channelName 信道名称，可自行定义，〖但是需要与两个地方保持一致〗，为以下两点：
                1、rabbit.bindings.output:
                    要和此处的 output 名称一致（不一定叫output）
                2、和代码当中消息发送的地方
                    1、@EnableBinding(Source.class)

                    2、public interface Source {
                    
                    	String OUTPUT = "output";
                    
                    	@Output(Source.OUTPUT)
                    	MessageChannel output();
                    }

                    3、@Resource
                    private MessageChannel output;
                    
                    说明：
                        第一点：消息的发送类上都会添加 @EnableBinding(Source.class) 注解，参数的class即第二点
                        第二点：发送消息 channel声明，此处的 String OUTPUT = "output"; 【需要和配置中的 rabbit.bindings.output，output保持一致】
            2、bindings.output.destination：
                声明一个交换机，studyExchange 为交换机的名称
            3、bindings.output.bander：
                指定当前 channel 通道使用的绑定器 defaultRabbit（使用哪个MQ环境），【需要和 binders.defaultRabbit 保持一致】
　　　　　　        
        rabbit：
            感觉主要是配置和 RabbitMq 相关的一些配置，像交换机的类型，可能kafka及其他的MQ根本没有
              rabbit:
                bindings:
                  output:       -----〉 channelName 需要和 bindings.output 保持一致
                    producer:   -----〉 表明是一个生产者
                      routing-key-expression: headers.type   ----〉发送的消息根据什么路由，此处就是：取消息headers，里的 type 对应的值作为路由key（发送消息是放置进去的）
                      exchangeType: direct                   ----〉 声明交换机的类型

   ```