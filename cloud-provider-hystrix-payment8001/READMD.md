#服务降级
 ## 服务端降级
 * 启动类激活 @EnableCircuitBreaker
 * 指定降级方法 @HystrixCommand
     ```
        示例: 
          @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }) 
            fallbackMethod: 指定降级后调用的方法
            commandProperties: 指定什么情况下降级的属性
     