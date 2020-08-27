package com.cca.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author cca
 * @date 2020/8/26 19:41
 */
@RestController
@Slf4j
public class OrderZkController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_SERVICE = "http://cloud-provider-payment";


    @GetMapping("/consumer/payment/zk")
    public String getPayment() {
        log.info("zk 消费者");
        return restTemplate.getForObject(PAYMENT_SERVICE + "/payment/zk", String.class);
    }
}
