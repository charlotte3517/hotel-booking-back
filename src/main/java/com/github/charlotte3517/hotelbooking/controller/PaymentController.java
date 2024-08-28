package com.github.charlotte3517.hotelbooking.controller;

import com.github.charlotte3517.hotelbooking.payment.Payment;
import com.github.charlotte3517.hotelbooking.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }
}
