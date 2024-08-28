package com.github.charlotte3517.hotelbooking.log.model;

import java.util.Date;

public class ExternalRequestLog {
    private Long requestId;
    private String requestMethod;
    private String requestUrl;
    private String requestHeaders;
    private String requestBody;
    private Integer responseStatus;
    private String responseHeaders;
    private String responseBody;
    private String clientIp;
    private Date requestTime;
    private Date responseTime;
    private Date createdAt;

    public Long getRequestId() {
        return requestId;
    }

    public ExternalRequestLog setRequestId(Long requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public ExternalRequestLog setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public ExternalRequestLog setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        return this;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public ExternalRequestLog setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
        return this;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public ExternalRequestLog setRequestBody(String requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public ExternalRequestLog setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
        return this;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public ExternalRequestLog setResponseHeaders(String responseHeaders) {
        this.responseHeaders = responseHeaders;
        return this;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public ExternalRequestLog setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        return this;
    }

    public String getClientIp() {
        return clientIp;
    }

    public ExternalRequestLog setClientIp(String clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public ExternalRequestLog setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
        return this;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public ExternalRequestLog setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ExternalRequestLog setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
