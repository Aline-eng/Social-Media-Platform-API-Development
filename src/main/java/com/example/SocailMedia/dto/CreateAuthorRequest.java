package com.example.SocailMedia.dto;

import lombok.Data;

@Data
public class CreateAuthorRequest {

    private String fullName;
    private String username;
    private String email;

}
