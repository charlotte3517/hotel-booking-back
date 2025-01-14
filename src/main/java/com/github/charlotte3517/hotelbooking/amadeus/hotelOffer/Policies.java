package com.github.charlotte3517.hotelbooking.amadeus.hotelOffer;

import java.util.List;

public class Policies {
    private List<Cancellation> cancellations;
    private String paymentType;

    public List<Cancellation> getCancellations() {
        return cancellations;
    }

    public void setCancellations(List<Cancellation> cancellations) {
        this.cancellations = cancellations;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
