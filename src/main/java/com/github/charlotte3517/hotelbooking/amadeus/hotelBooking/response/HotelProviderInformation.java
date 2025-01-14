package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response;

public class HotelProviderInformation {
    private String hotelProviderCode;
    private String confirmationNumber;

    public String getHotelProviderCode() {
        return hotelProviderCode;
    }

    public void setHotelProviderCode(String hotelProviderCode) {
        this.hotelProviderCode = hotelProviderCode;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}
