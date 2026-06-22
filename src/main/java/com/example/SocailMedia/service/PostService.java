package com.example.SocailMedia.service;

import com.example.SocailMedia.dto.CreatePostRequest;
import com.example.SocailMedia.dto.PostResponse;
import com.example.SocailMedia.model.Author;
import com.example.SocailMedia.model.Post;
import com.example.SocailMedia.repository.AuthorRepository;
import com.example.SocailMedia.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostResponse createPost(CreatePostRequest request) {
        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setVisibility(request.getVisibility());
        post.setCreatedBy(author);
        post.setCreatedAt(LocalDateTime.now());

        Post saved = postRepository.save(post);
        PostResponse response = new PostResponse();
        response.setTitle(saved.getTitle());
        response.setContent(saved.getContent());
        response.setVisibility(saved.getVisibility());
        response.setCreatedAt(saved.getCreatedAt());
        response.setCreatedBy(author.getFullName());

        return response;

    }
}
