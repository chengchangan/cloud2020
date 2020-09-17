# spring-config 手动版刷新配置
  * 手动版流程
   ```
        1、通过发送刷新请求到client端，获取最新的数据
        2、通过注解@RefreshScope，用心的数据构建一个配置bean，从而获取最新数据
   ```

  * bootstrap.yml 增加 actuator 监控报漏点
  * 添加 @RefreshScope 支持自动刷新
  ```
    添加监控暴漏点：
        management:
          endpoints:
            web:
              exposure:
                include: "*"
        @RefreshScope的理解：
              1、如果不使用这个注解，则每次获取配置，都是从第一次装配进去的bean里获取的
              2、加了这个注解，每次都会生成一个代理对象，重新获取信息进行装配
              3、@RefreshScope 本质是 @Scope，对 @Scope的封装，关键是：ScopedProxyMode.TARGET_CLASS
        手动刷新：
            POST http://localhost:3377/actuator/refresh，刷新对应的client
  ```
 # spring-config 自动版刷新配置
   * 自动版流程
   ```
        1、通过发送请求到 server端，地址：http://{config-server-IP:port}/actuator/bus-refresh
        2、server端感知数据变更，以消息的方式，广播通知所有client端
        3、client端获取最新数据
   ```
   * 添加bus-amqp 依赖支持
   * 配置rabbitMQ 
   * 增加Server端 actuator 监控报漏点 include: 'bus-refresh'
   ```
        1、添加依赖：
            <!-- 消息总线 amqp实现-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-bus-amqp</artifactId>
            </dependency>
        2、配置mq连接信息:
            spring: 
              rabbitmq:
                host: 120.79.136.105
                port: 5672
                username: guest
                password: guest
        3、config-server增加监控点
            management:
              endpoints:
                web:
                  exposure:
                    include: 'bus-refresh'
　　```
