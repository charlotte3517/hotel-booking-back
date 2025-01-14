package com.github.charlotte3517.hotelbooking.amadeus.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {
    @JsonProperty("type")
    private String type;

    @JsonProperty("username")
    private String username;

    @JsonProperty("application_name")
    private String applicationName;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("state")
    private String state;

    @JsonProperty("scope")
    private String scope;

    public String getType() {
        return type;
    }

    public TokenResponse setType(String type) {
        this.type = type;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public TokenResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public TokenResponse setApplicationName(String applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public TokenResponse setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public TokenResponse setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public TokenResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public TokenResponse setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getState() {
        return state;
    }

    public TokenResponse setState(String state) {
        this.state = state;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public TokenResponse setScope(String scope) {
        this.scope = scope;
        return this;
    }
}
