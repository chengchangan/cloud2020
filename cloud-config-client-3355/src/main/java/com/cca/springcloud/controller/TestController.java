package com.cca.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cca
 * @date 2020/9/10 14:52
 */
@RestController
@Slf4j
@RefreshScope   // 这个注解的原理，如果不适用这个注解，则每次获取配置，都是从第一次装配进去的对象里获取的，加了这个注解，每次都会生成一个代理对象，重新获取信息进行装配
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;


    @GetMapping("/test")
    public String test() {
        return "我是 config-client，我的端口号是：" + serverPort;
    }

    @GetMapping("/info")
    public String configInfo() {
        return "我是 config-client，我的配置：" + configInfo;
    }

}
