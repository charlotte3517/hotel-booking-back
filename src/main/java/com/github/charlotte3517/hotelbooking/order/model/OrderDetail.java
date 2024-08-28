package com.github.charlotte3517.hotelbooking.order.model;

import java.math.BigDecimal;

public class OrderDetail {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer roomTypeId;
    private Integer roomQuantity;
    private BigDecimal price;
    public Integer getOrderDetailId() {
        return orderDetailId;
    }
    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(Integer roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderDetail setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}