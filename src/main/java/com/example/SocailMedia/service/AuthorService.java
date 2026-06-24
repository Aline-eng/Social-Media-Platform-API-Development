package com.example.SocailMedia.service;

import com.example.SocailMedia.dto.AuthorResponse;
import com.example.SocailMedia.dto.CreateAuthorRequest;
import com.example.SocailMedia.dto.UpdateAuthorRequest;
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


    private AuthorResponse toResponse(String message, Author author) {
        AuthorResponse response = new AuthorResponse();
        response.setMessage(message);
        response.setFullName(author.getFullName());
        response.setUsername(author.getUsername());
        response.setEmail(author.getEmail());
        response.setCreatedAt(author.getCreatedAt());
        return response;
    }

  
    public AuthorResponse createAuthor(CreateAuthorRequest request) {
        Author author = new Author();
        author.setFullName(request.getFullName());
        author.setUsername(request.getUsername());
        author.setEmail(request.getEmail());
        author.setCreatedAt(LocalDateTime.now());

        Author saved = authorRepository.save(author);
        return toResponse("Author created successfully", saved);
    }

 
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public AuthorResponse getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        return toResponse("Author found", author);
    }

    public AuthorResponse getAuthorByUsername(String username) {
        Author author = authorRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Author not found with username: " + username));
        return toResponse("Author found", author);
    }

    public AuthorResponse getAuthorByEmail(String email) {
        Author author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Author not found with email: " + email));
        return toResponse("Author found", author);
    }


    public List<AuthorResponse> getAuthorsByPeriod(LocalDateTime start, LocalDateTime end) {
        return authorRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(a -> toResponse("", a))
                .toList();
    }

    public AuthorResponse updateAuthor(Long id, UpdateAuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        if (request.getFullName() != null) author.setFullName(request.getFullName());
        if (request.getUsername() != null) author.setUsername(request.getUsername());
        if (request.getEmail() != null)    author.setEmail(request.getEmail());
        if (request.getBio() != null)      author.setBio(request.getBio());

        Author updated = authorRepository.save(author);
        return toResponse("Author updated successfully", updated);
    }

    public String deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
        return "Author deleted successfully";
    }
}
