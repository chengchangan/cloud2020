package com.cca.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cca
 * @date 2020/9/10 14:52
 */
@RestController
@Slf4j
public class TestController {

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/test")
    public String test() {
        return "我是 config-client，我的端口号是：" + serverPort;
    }

}
