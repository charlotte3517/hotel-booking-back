package com.github.charlotte3517.hotelbooking.payment;

import com.github.charlotte3517.hotelbooking.dao.PaymentDao;
import com.github.charlotte3517.hotelbooking.exception.HBException;
import com.github.charlotte3517.hotelbooking.payment.Payment;
import com.github.charlotte3517.hotelbooking.payment.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentDao paymentDao;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        // MockitoAnnotations.openMocks(this); // No need when using @ExtendWith(MockitoExtension.class)
    }

    @Test
    void createPaymentSuccessfully() {
        Payment payment = new Payment();
        payment.setOrderId(1);
        payment.setPaymentAmount(BigDecimal.valueOf(100.0));
        payment.setPaymentMethod("Credit Card");

        when(paymentDao.checkIfOrderIdExists(anyInt())).thenReturn(0);

        Payment result = paymentService.createPayment(payment);

        assertNotNull(result);
        assertEquals(1, result.getOrderId());
        assertEquals(BigDecimal.valueOf(100.0), result.getPaymentAmount());
        assertEquals("Credit Card", result.getPaymentMethod());
        assertNotNull(result.getPaymentDate());

        verify(paymentDao, times(1)).insertPayment(any(Payment.class), anyInt());
    }

    @Test
    void createPaymentThrowsExceptionForExistingOrderId() {
        Payment payment = new Payment();
        payment.setOrderId(1);
        payment.setPaymentAmount(BigDecimal.valueOf(100.0));
        payment.setPaymentMethod("Credit Card");

        when(paymentDao.checkIfOrderIdExists(anyInt())).thenReturn(1);

        HBException exception = assertThrows(HBException.class, () -> {
            paymentService.createPayment(payment);
        });

        assertEquals("Order ID is already paid.", exception.getMessage());

        verify(paymentDao, times(0)).insertPayment(any(Payment.class), anyInt());
    }


    @Test
    void createPaymentHandlesDaoException() {
        Payment payment = new Payment();
        payment.setOrderId(1);
        payment.setPaymentAmount(BigDecimal.valueOf(100.0));
        payment.setPaymentMethod("Credit Card");

        when(paymentDao.checkIfOrderIdExists(anyInt())).thenReturn(0);
        doThrow(new RuntimeException("Database error")).when(paymentDao).insertPayment(any(Payment.class), anyInt());

        HBException exception = assertThrows(HBException.class, () -> {
            paymentService.createPayment(payment);
        });

        assertEquals("An error occurred while processing the payment.", exception.getMessage());

        verify(paymentDao, times(1)).insertPayment(any(Payment.class), anyInt());
    }

    @Test
    void validatePaymentInfoThrowsExceptionForNullFields() {
        Payment payment = new Payment();

        HBException exception = assertThrows(HBException.class, () -> {
            paymentService.createPayment(payment);
        });

        assertTrue(exception.getMessage().contains("Order ID cannot be null"));
        assertTrue(exception.getMessage().contains("Payment amount cannot be null"));
        assertTrue(exception.getMessage().contains("Payment method cannot be null"));

        verify(paymentDao, times(0)).insertPayment(any(Payment.class), anyInt());
    }

    @Test
    void createPaymentThrowsExceptionForInvalidOrderId() {
        Payment payment = new Payment();
        payment.setOrderId(null);
        payment.setPaymentAmount(BigDecimal.valueOf(100.0));
        payment.setPaymentMethod("Credit Card");

        HBException exception = assertThrows(HBException.class, () -> {
            paymentService.createPayment(payment);
        });

        assertTrue(exception.getMessage().contains("Order ID cannot be null"));

        verify(paymentDao, times(0)).insertPayment(any(Payment.class), anyInt());
    }

    @Test
    void createPaymentThrowsExceptionForNullPaymentAmount() {
        Payment payment = new Payment();
        payment.setOrderId(1);
        payment.setPaymentAmount(null);
        payment.setPaymentMethod("Credit Card");

        HBException exception = assertThrows(HBException.class, () -> {
            paymentService.createPayment(payment);
        });

        assertTrue(exception.getMessage().contains("Payment amount cannot be null"));

        verify(paymentDao, times(0)).insertPayment(any(Payment.class), anyInt());
    }

    @Test
    void createPaymentThrowsExceptionForNullPaymentMethod() {
        Payment payment = new Payment();
        payment.setOrderId(1);
        payment.setPaymentAmount(BigDecimal.valueOf(100.0));
        payment.setPaymentMethod(null);

        HBException exception = assertThrows(HBException.class, () -> {
            paymentService.createPayment(payment);
        });

        assertTrue(exception.getMessage().contains("Payment method cannot be null"));

        verify(paymentDao, times(0)).insertPayment(any(Payment.class), anyInt());
    }
}