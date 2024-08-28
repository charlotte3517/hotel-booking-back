package com.github.charlotte3517.hotelbooking.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer hotelId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date checkInDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date checkOutDate;
    private BigDecimal totalPrice;
    private String paymentMethod;
    private List<OrderDetail> orderDetails;

    public Integer getOrderId() {
        return orderId;
    }

    public Order setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public Order setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Order setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
        return this;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Order setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Order setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Order setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public Order setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
        return this;
    }
}