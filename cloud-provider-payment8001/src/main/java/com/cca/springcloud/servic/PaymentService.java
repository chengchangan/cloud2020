package com.cca.springcloud.servic;

import com.cca.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
