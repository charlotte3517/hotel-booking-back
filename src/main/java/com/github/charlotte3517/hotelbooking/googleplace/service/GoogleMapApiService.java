package com.github.charlotte3517.hotelbooking.googleplace.service;

import com.github.charlotte3517.hotelbooking.googleplace.GooglePlacesProperties;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.findplace.PlaceResponse;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.placedetail.PlaceDetailsResponse;
import com.github.charlotte3517.hotelbooking.common.ApiService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleMapApiService extends ApiService {

    private final GooglePlacesProperties properties;

    public GoogleMapApiService(RestTemplate restTemplate, GooglePlacesProperties properties) {
        super(restTemplate);
        this.properties = properties;
    }

    public PlaceResponse findPlaceFromText(String input) {

        String url = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl())
                .path("/findplacefromtext/json")
                .queryParam("input", input)
                .queryParam("inputtype", "textquery")
                .queryParam("fields", "name,place_id,photos,rating,formatted_address,price_level")
                .queryParam("language", properties.getLanguage())
                .queryParam("key", properties.getApiKey())
                .build(false)
                .toUriString();

        return callApi(url, null, PlaceResponse.class, HttpMethod.GET);
    }

    public PlaceDetailsResponse getPlaceDetails(String placeId) {

        String url = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl())
                .path("/details/json")
                .queryParam("fields", "name,reviews,price_level")
                .queryParam("place_id", placeId)
                .queryParam("language", properties.getLanguage())
                .queryParam("key", properties.getApiKey())
                .build(false)
                .toUriString();

        return callApi(url, null, PlaceDetailsResponse.class, HttpMethod.GET);
    }
}
