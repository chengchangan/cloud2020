package com.cca.springcloud.controller;

import com.cca.springcloud.entities.CommonResult;
import com.cca.springcloud.entities.Payment;
import com.cca.springcloud.service.PaymentServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cca
 * @date 2020/8/28 9:19
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private PaymentServiceFeign paymentServiceFeign;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentServiceFeign.getPaymentById(id);
    }


    public static void main(String[] args) {
        // todo 单元测试调用 openFeign

    }


}
