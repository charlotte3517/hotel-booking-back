package com.github.charlotte3517.hotelbooking.googleplace;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public abstract class ApiService {

    protected final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T callApi(String url, Object request, Class<T> responseClass, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, responseClass);

        return responseEntity.getBody();
    }
}