package com.example.SocailMedia.controller;

import com.example.SocailMedia.dto.CreatePostRequest;
import com.example.SocailMedia.dto.PostResponse;
import com.example.SocailMedia.dto.UpdatePostRequest;
import com.example.SocailMedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostResponse createPost(@Validated @RequestBody CreatePostRequest request) {
        return postService.createPost(request);
    }

    @GetMapping
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    
    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

   
    @GetMapping("/author/{authorId}")
    public List<PostResponse> getPostsByAuthor(@PathVariable Long authorId) {
        return postService.getPostsByAuthor(authorId);
    }

   
    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable Long id,
                                   @Validated @RequestBody UpdatePostRequest request) {
        return postService.updatePost(id, request);
    }

 
    @DeleteMapping("/{id}")
    public String deletePostById(@PathVariable Long id) {
        return postService.deletePostById(id);
    }

    @DeleteMapping("/author/{authorId}")
    public String deletePostsByAuthor(@PathVariable Long authorId) {
        return postService.deletePostsByAuthor(authorId);
    }

    
    @DeleteMapping("/period")
    public String deletePostsByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return postService.deletePostsByPeriod(start, end);
    }
}
