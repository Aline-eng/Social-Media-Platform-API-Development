package com.example.SocailMedia.repository;

import com.example.SocailMedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedById(Long authorId);
    void deleteByCreatedAtBetween(
            LocalDateTime start, LocalDateTime end
    );
}