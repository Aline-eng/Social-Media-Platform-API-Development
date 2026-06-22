package com.example.SocailMedia.service;

import com.example.SocailMedia.dto.CreatePostRequest;
import com.example.SocailMedia.dto.PostResponse;
import com.example.SocailMedia.dto.UpdatePostRequest;
import com.example.SocailMedia.model.Author;
import com.example.SocailMedia.model.Post;
import com.example.SocailMedia.repository.AuthorRepository;
import com.example.SocailMedia.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    private PostResponse toResponse(String message, Post post) {
        PostResponse response = new PostResponse();
        response.setMessage(message);
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setVisibility(post.getVisibility());
        response.setCreatedAt(post.getCreatedAt());
        response.setCreatedBy(post.getCreatedBy().getFullName());
        return response;
    }


    public PostResponse createPost(CreatePostRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + request.getAuthorId()));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setVisibility(request.getVisibility());
        post.setCreatedBy(author);
        post.setCreatedAt(LocalDateTime.now());

        Post saved = postRepository.save(post);
        return toResponse("Post created successfully", saved);
    }


    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(p -> toResponse("", p))
                .toList();
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return toResponse("Post found", post);
    }

    public List<PostResponse> getPostsByAuthor(Long authorId) {
        return postRepository.findByCreatedById(authorId)
                .stream()
                .map(p -> toResponse("", p))
                .toList();
    }

    public PostResponse updatePost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));

        if (request.getTitle() != null)   post.setTitle(request.getTitle());
        if (request.getContent() != null) post.setContent(request.getContent());

        Post updated = postRepository.save(post);
        return toResponse("Post updated successfully", updated);
    }

    public String deletePostById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
        return "Post deleted successfully";
    }

    public String deletePostsByAuthor(Long authorId) {
        List<Post> posts = postRepository.findByCreatedById(authorId);
        postRepository.deleteAll(posts);
        return "All posts by author deleted successfully";
    }

    @Transactional
    public String deletePostsByPeriod(LocalDateTime start, LocalDateTime end) {
        postRepository.deleteByCreatedAtBetween(start, end);
        return "Posts in the given period deleted successfully";
    }
}
