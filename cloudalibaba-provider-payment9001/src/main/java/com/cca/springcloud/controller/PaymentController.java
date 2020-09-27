package com.cca.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author cca
 * @date 2020/9/27 19:22
 */

@Slf4j
@RestController
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/payment/get/nacos/{id}")
    public String getPayment(@PathVariable("id") String id) {
        log.info("************************** 请求进入 Provider");
        String string = UUID.randomUUID().toString();
        return string + "serverPort : " + serverPort + "id : " + id;
    }


}
