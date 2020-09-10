# spring-config 手动版刷新配置
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
  ```
 
