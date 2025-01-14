package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response;

public class Cancellation {
    private CancellationsDescription description;
    private String type;

    public CancellationsDescription getDescription() {
        return description;
    }

    public void setDescription(CancellationsDescription description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
