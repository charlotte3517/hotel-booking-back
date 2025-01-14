package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.request;

public class Payment {
    private String method;
    private PaymentCard paymentCard;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }
}
