package com.cca.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    private Integer count = 0;

    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        Thread.sleep(5000);
        log.info("-------请求次数: {}, 线程名称: {}", ++count, Thread.currentThread().getName());
        return "-----testA ,次数" + count;
    }

    @GetMapping("/testB")
    public String testB() {
        return "-----testB";
    }
}
