package com.github.charlotte3517.hotelbooking.googleplace;

import java.math.BigDecimal;

public class GoogleReview {
    private Integer googleReviewId;
    private String placeId;
    private String authorName;
    private BigDecimal rating;
    private String content;
    private String reviewTime;

    public Integer getGoogleReviewId() {
        return googleReviewId;
    }

    public GoogleReview setGoogleReviewId(Integer googleReviewId) {
        this.googleReviewId = googleReviewId;
        return this;
    }

    public String getPlaceId() {
        return placeId;
    }

    public GoogleReview setPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public GoogleReview setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public GoogleReview setRating(BigDecimal rating) {
        this.rating = rating;
        return this;
    }

    public String getContent() {
        return content;
    }

    public GoogleReview setContent(String content) {
        this.content = content;
        return this;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public GoogleReview setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
        return this;
    }
}