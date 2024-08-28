package com.github.charlotte3517.hotelbooking.utils;

import org.springframework.http.HttpMethod;
public class callApi {


    private <T> T callApi(String url, Object request, Class<T> responseClass, String token, IntegrationLog integrationLog, HttpMethod httpMethod) throws Exception {
        String responseJson = RestfulUtils.sendRequest(url, request, token, httpMethod);
        T responseObject = YourJsonParser.parseJson(responseJson, responseClass);
        integrationLog.logRequest(url, request);
        integrationLog.logResponse(responseJson);
        return responseObject;
    }

    static class RestfulUtils {
        static String sendRequest(String url, Object request, String token, HttpMethod httpMethod) {
            return "{\"response\": \"This is a mock response.\"}";
        }
    }

    static class IntegrationLog {
        void logRequest(String url, Object request) {
        }

        void logResponse(String responseJson) {
        }
    }

    static class YourJsonParser {
        static <T> T parseJson(String json, Class<T> responseClass) {
            return responseClass.cast(new Object());
        }
    }






}
