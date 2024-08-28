package com.github.charlotte3517.hotelbooking.payment;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private Integer paymentId;
    private Integer orderId;
    private Date paymentDate;
    private BigDecimal paymentAmount;
    private String paymentMethod;

    public Integer getPaymentId() {
        return paymentId;
    }

    public Payment setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Payment setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public Payment setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public Payment setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Payment setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }
}
