package com.cca.springcloud.controller;

import com.cca.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author cca
 * @date 2020/8/27 18:54
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_SERVICE = "http://cloud-payment-service";


    @GetMapping("/consumer/consul/get")
    public CommonResult<String> getPayment() {
        log.info("consul consumer begin ! ! !");
        String result = restTemplate.getForObject(PAYMENT_SERVICE + "/payment/consul", String.class);
        return CommonResult.success(result);
    }
}
