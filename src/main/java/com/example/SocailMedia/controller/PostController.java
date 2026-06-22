package com.example.SocailMedia.controller;


import com.example.SocailMedia.dto.CreatePostRequest;
import com.example.SocailMedia.dto.PostResponse;
import com.example.SocailMedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping
    public PostResponse createPost(@Validated @RequestBody CreatePostRequest request) {
        return postService.createPost(request);
    }
}
