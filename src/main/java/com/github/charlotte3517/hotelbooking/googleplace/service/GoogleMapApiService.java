package com.github.charlotte3517.hotelbooking.googleplace.service;

import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.findplace.PlaceResponse;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.placedetail.PlaceDetailsResponse;
import com.github.charlotte3517.hotelbooking.googleplace.ApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleMapApiService extends ApiService {

    @Value("${google.places.baseUrl}")
    private String baseUrl;

    @Value("${google.places.apiKey}")
    private String apiKey;

    @Value("${google.places.language}")
    private String language;

    public GoogleMapApiService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public PlaceResponse findPlaceFromText(String input) {

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/findplacefromtext/json")
                .queryParam("input", input)
                .queryParam("inputtype", "textquery")
                .queryParam("fields", "name,place_id,photos,rating,formatted_address,price_level")
                .queryParam("language", language)
                .queryParam("key", apiKey)
                .build(false)
                .toUriString();

        return callApi(url, null, PlaceResponse.class, HttpMethod.GET);
    }

    public PlaceDetailsResponse getPlaceDetails(String placeId) {

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/details/json")
                .queryParam("fields", "name,reviews,price_level")
                .queryParam("place_id", placeId)
                .queryParam("language", language)
                .queryParam("key", apiKey)
                .build(false)
                .toUriString();

        return callApi(url, null, PlaceDetailsResponse.class, HttpMethod.GET);
    }
}
