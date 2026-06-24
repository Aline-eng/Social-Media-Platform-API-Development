package com.example.SocailMedia.controller;

import com.example.SocailMedia.dto.AuthorResponse;
import com.example.SocailMedia.dto.CreateAuthorRequest;
import com.example.SocailMedia.dto.UpdateAuthorRequest;
import com.example.SocailMedia.model.Author;
import com.example.SocailMedia.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public AuthorResponse createAuthor(@Validated @RequestBody CreateAuthorRequest request) {
        return authorService.createAuthor(request);
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }


    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/username/{username}")
    public AuthorResponse getAuthorByUsername(@PathVariable String username) {
        return authorService.getAuthorByUsername(username);
    }

   
    @GetMapping("/email/{email}")
    public AuthorResponse getAuthorByEmail(@PathVariable String email) {
        return authorService.getAuthorByEmail(email);
    }


    @GetMapping("/period")
    public List<AuthorResponse> getAuthorsByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return authorService.getAuthorsByPeriod(start, end);
    }

  
    @PutMapping("/{id}")
    public AuthorResponse updateAuthor(@PathVariable Long id,
                                       @Validated @RequestBody UpdateAuthorRequest request) {
        return authorService.updateAuthor(id, request);
    }

  
    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id);
    }
}
