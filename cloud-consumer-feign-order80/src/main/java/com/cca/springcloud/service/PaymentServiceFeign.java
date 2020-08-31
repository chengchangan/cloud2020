package com.cca.springcloud.service;

import com.cca.springcloud.config.FeignInterceptorConfig;
import com.cca.springcloud.entities.CommonResult;
import com.cca.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author cca
 * @date 2020/8/28 9:40
 */
@FeignClient(value = "cloud-payment-service", configuration = FeignInterceptorConfig.class)
public interface PaymentServiceFeign {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

}
