package com.cca.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author cca
 * @date 2020/9/27 19:41
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_SERVICE = "http://nacos-payment-provider-service";


    @GetMapping("/order/get/nacos/{id}")
    public String getOrder(@PathVariable("id") String id) {
        log.info("******************* 请求进入 Consumer ");
        return restTemplate.getForObject(PAYMENT_SERVICE + "/payment/get/nacos/" + id, String.class);
    }
}
