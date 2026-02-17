package com.zhcode.personalbloggingapi.dto;

public class LoginResponse {
    private final Long userId;
    private final String username;
    private final String token;

    public LoginResponse(Long userId, String username, String token) {
        this.userId = userId;
        this.username = username;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
