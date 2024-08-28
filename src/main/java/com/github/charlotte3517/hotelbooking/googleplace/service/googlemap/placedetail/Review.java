package com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.placedetail;

import java.math.BigDecimal;

public class Review {
    private String author_name;
    private String author_url;
    private String language;
    private String original_language;
    private String profile_photo_url;
    private BigDecimal rating;
    private String relative_time_description;
    private String text;
    private Long time;
    private Boolean translated;

    public String getAuthor_name() {
        return author_name;
    }

    public Review setAuthor_name(String author_name) {
        this.author_name = author_name;
        return this;
    }

    public String getAuthor_url() {
        return author_url;
    }

    public Review setAuthor_url(String author_url) {
        this.author_url = author_url;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Review setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public Review setOriginal_language(String original_language) {
        this.original_language = original_language;
        return this;
    }

    public String getProfile_photo_url() {
        return profile_photo_url;
    }

    public Review setProfile_photo_url(String profile_photo_url) {
        this.profile_photo_url = profile_photo_url;
        return this;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public Review setRating(BigDecimal rating) {
        this.rating = rating;
        return this;
    }

    public String getRelative_time_description() {
        return relative_time_description;
    }

    public Review setRelative_time_description(String relative_time_description) {
        this.relative_time_description = relative_time_description;
        return this;
    }

    public String getText() {
        return text;
    }

    public Review setText(String text) {
        this.text = text;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public Review setTime(Long time) {
        this.time = time;
        return this;
    }

    public Boolean getTranslated() {
        return translated;
    }

    public Review setTranslated(Boolean translated) {
        this.translated = translated;
        return this;
    }
}
