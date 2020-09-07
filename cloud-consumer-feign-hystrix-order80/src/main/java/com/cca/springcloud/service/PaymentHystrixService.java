package com.cca.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-hystrix-payment-service", fallback = PaymentHystrixService.PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);

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
}
