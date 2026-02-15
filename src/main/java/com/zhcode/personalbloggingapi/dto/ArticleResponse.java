package com.zhcode.personalbloggingapi.dto;

import java.time.Instant;

public class ArticleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final Instant createdAt;
    private final Instant updatedAt;

    public ArticleResponse(Long id, String title, String content, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
