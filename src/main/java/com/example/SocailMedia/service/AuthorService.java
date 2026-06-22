package com.example.SocailMedia.service;

import com.example.SocailMedia.dto.AuthorResponse;
import com.example.SocailMedia.dto.CreateAuthorRequest;
import com.example.SocailMedia.model.Author;
import com.example.SocailMedia.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorResponse createAuthor(CreateAuthorRequest request) {
        Author author = new Author();
        author.setFullName(request.getFullName());
        author.setUsername(request.getUsername());
        author.setEmail(request.getEmail());
        author.setCreatedAt(LocalDateTime.now());
        Author saved = authorRepository.save(author);
        AuthorResponse response = new AuthorResponse();
        response.setMessage("Author created successfully");
        response.setFullName(saved.getFullName());
        response.setUsername(saved.getUsername());
        response.setEmail(saved.getEmail());
        response.setCreatedAt(saved.getCreatedAt());

        return response;
    }
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
    public Author getAuthorByUsername(String username) {
        return authorRepository.findByUsername(username).orElse(null);
    }
    public Author getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email).orElse(null);
    }

}
