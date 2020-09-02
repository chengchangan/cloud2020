#服务降级
 ## 消费端降级
 * 启动类激活 @EnableHystrix
 * 指定降级方法 @HystrixCommand
     ```
        示例: 
             @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
                     @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")  //3秒钟以内就是正常
             })
            fallbackMethod: 指定降级后调用的方法
            commandProperties: 指定什么情况下降级的属性
 ## 疑问:
    * 为什么课程里讲解的需要开启yml 配置
        feign:
          hystrix:
            enabled: true #如果处理自身的容错就开启。开启方式与生产端不一样。
      【我不开启一样生效】 