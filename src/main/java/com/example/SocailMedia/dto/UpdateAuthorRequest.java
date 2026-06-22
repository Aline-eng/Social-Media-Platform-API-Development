package com.example.SocailMedia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAuthorRequest {

    @Size(min = 5, message = "Full name must be at least 5 characters")
    private String fullName;

    private String username;

    @Email(message = "Email must be valid")
    private String email;

    private String bio;
}
