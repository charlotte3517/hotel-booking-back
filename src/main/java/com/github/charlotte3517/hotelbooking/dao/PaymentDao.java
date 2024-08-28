package com.github.charlotte3517.hotelbooking.dao;

import com.github.charlotte3517.hotelbooking.payment.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentDao {
    List<Payment> getAllPayments();

    Payment getPaymentById(@Param("paymentId") Integer id);

    Integer insertPayment(@Param("payment") Payment payment, @Param("userId") Integer userId);

    void updatePayment(@Param("payment") Payment payment, @Param("userId") Integer userId);

    void deletePayment(@Param("paymentId") Integer id);

    Integer checkIfOrderIdExists(@Param("orderId") Integer orderId);
}
