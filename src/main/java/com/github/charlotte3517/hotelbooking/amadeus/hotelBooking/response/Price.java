package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response;

import java.util.List;

public class Price {
    private String base;
    private String currency;
    private String sellingTotal;
    private String total;
    private List<Tax> taxes;
    private Variations variations;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSellingTotal() {
        return sellingTotal;
    }

    public void setSellingTotal(String sellingTotal) {
        this.sellingTotal = sellingTotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Tax> taxes) {
        this.taxes = taxes;
    }

    public Variations getVariations() {
        return variations;
    }

    public void setVariations(Variations variations) {
        this.variations = variations;
    }
}
