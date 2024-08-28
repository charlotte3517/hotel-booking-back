package com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.placedetail;

import java.util.List;

public class PlaceDetailsResponse {
    private List<String> html_attributions;
    private Result result;
    private String status;

    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<String> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


