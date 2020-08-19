package com.cca.springcloud.controller;

import com.cca.springcloud.entities.CommonResult;
import com.cca.springcloud.entities.Payment;
import com.cca.springcloud.servic.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果：{}", result);
        if (result > 0) {
            return new CommonResult<Integer>(200, "成功", result);
        } else {
            return new CommonResult<Integer>(444, "创建失败");

        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：{}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "成功", payment);
        } else {
            return new CommonResult<Payment>(444, "失败，id：" + id);
        }
    }

}
