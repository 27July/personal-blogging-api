package com.zhcode.personalbloggingapi.dto;

import java.time.Instant;

public class UserResponse {
    private final Long id;
    private final String username;
    private final Instant createdAt;

    public UserResponse(Long id, String username, Instant createdAt) {
        this.id = id;
        this.username = username;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
