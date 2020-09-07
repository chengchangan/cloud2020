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
 * 疑问:
    * 为什么课程里讲解的需要开启yml 配置
        feign:
          hystrix:
            enabled: true #如果处理自身的容错就开启。开启方式与生产端不一样。
      【???我不开启一样生效】 
      
 ##  全局降级方法
   * 类上添加 @DefaultProperties() 指定全局降级方法
   * 方法上添加 @HystrixCommand 表示启用降级，但是不指定降级方法，则使用全局降级方法
       ```
        示例：
            1：
                @DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
                public class OrderController {
                
                }
                payment_Global_FallbackMethod：表示全局方法名
            2：
                在方法上添加 @HystrixCommand　，不带参数，如果注解内指定了降级方法，则使用指定的降级方法

 ##  远程接口处统一处理降级
   * 实现远程服务接口,返回降级信息
   * @FeignClient 注解上添加降级处理类
        ```
        示例：
            1：
                @Component
                class PaymentFallbackService implements PaymentHystrixService {
            
                    @Override
                    public String paymentInfo_OK(Integer id) {
                        return "-----PaymentFallbackService fall back-paymentInfo_OK , (┬＿┬)";
                    }
            
                    @Override
                    public String paymentInfo_TimeOut(Integer id) {
                        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut , (┬＿┬)";
                    }
                }
            2：
                @FeignClient(value = "cloud-hystrix-payment-service", fallback = PaymentHystrixService.PaymentFallbackService.class)