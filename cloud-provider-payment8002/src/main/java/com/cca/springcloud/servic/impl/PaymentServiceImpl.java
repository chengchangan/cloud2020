package com.cca.springcloud.servic.impl;

import com.cca.springcloud.entities.Payment;
import org.springframework.stereotype.Service;
import com.cca.springcloud.dao.PaymentDao;
import com.cca.springcloud.servic.PaymentService;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
