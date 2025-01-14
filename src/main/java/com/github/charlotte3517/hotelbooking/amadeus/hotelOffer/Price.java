package com.github.charlotte3517.hotelbooking.amadeus.hotelOffer;

public class Price {
    private String currency;
    private String base;
    private String total;
    private Variations variations;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Variations getVariations() {
        return variations;
    }

    public void setVariations(Variations variations) {
        this.variations = variations;
    }
}

