package com.github.charlotte3517.hotelbooking.payment;

import com.github.charlotte3517.hotelbooking.dao.PaymentDao;
import com.github.charlotte3517.hotelbooking.exception.HBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private static final Integer DEFAULT_USER_ID = 0;

    @Autowired
    private PaymentDao paymentDao;

    private boolean validatePaymentInfo(Payment payment) {
        StringBuilder errors = new StringBuilder();

        if (payment.getOrderId() == null) {
            errors.append("Order ID cannot be null. ");
        }
        if (payment.getPaymentAmount() == null) {
            errors.append("Payment amount cannot be null. ");
        }
        if (payment.getPaymentMethod() == null) {
            errors.append("Payment method cannot be null. ");
        }

        if (errors.length() > 0) {
            throw new HBException(errors.toString());
        }
        return true;
    }

    @Override
    public Payment createPayment(Payment payment) {
        try {
            validatePaymentInfo(payment);
            if (checkIfOrderIdExists(payment.getOrderId())) {
                throw new HBException("Order ID is already paid.");
            }
            payment.setPaymentDate(new Date());
            paymentDao.insertPayment(payment, DEFAULT_USER_ID);
            payment.setPaymentId(payment.getPaymentId());
            return payment;
        } catch (HBException e) {
            throw e;
        } catch (Exception e) {
            throw new HBException("An error occurred while processing the payment.");
        }
    }


    private boolean checkIfOrderIdExists(Integer orderId) {
        try {
            Integer count = paymentDao.checkIfOrderIdExists(orderId);
            return count > 0;
        } catch (Exception e) {
            logger.error("Error occurred while checking if order ID exists: ", e);
            throw new HBException("An error occurred while verifying the order ID.");
        }
    }
}
