package com.github.charlotte3517.hotelbooking.common;

import io.micrometer.common.util.StringUtils;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

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

    public <T> T callApi(String url, String token, Object request, Class<T> responseClass, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (StringUtils.isNotBlank(token)){
            headers.setBearerAuth(token);
        }

        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, responseClass);
        return responseEntity.getBody();
    }

    public <T> T callApiWithForm(String url, Map<String, String> request, Class<T> responseClass, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formRequest = new LinkedMultiValueMap<>();
        formRequest.setAll(request);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formRequest, headers);

        ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, responseClass);

        return responseEntity.getBody();
    }
}