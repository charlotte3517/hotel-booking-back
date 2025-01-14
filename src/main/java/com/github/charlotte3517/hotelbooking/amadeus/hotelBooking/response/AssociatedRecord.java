package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response;

public class AssociatedRecord {
    private String reference;
    private String originSystemCode;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOriginSystemCode() {
        return originSystemCode;
    }

    public void setOriginSystemCode(String originSystemCode) {
        this.originSystemCode = originSystemCode;
    }
}
