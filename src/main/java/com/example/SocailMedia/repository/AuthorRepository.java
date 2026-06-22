package com.example.SocailMedia.repository;

import com.example.SocailMedia.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByUsername(String username);
    Optional<Author> findByEmail(String email);

    List<Author> findByCreatedAtBetween(
            LocalDateTime start, LocalDateTime end
    );
}