package com.example.SocailMedia.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private String message;
    private String title;
    private String content;
    private String visibility;
    private LocalDateTime createdAt;
    private String createdBy;
}
