package com.example.SocailMedia.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePostRequest {


    @Size(min = 10, max = 100, message = "Title must be between 10 and 100 characters")
    private String title;

    @Size(min = 50, max = 2000, message = "Content must be between 50 and 2000 characters")
    private String content;
}
