package com.example.SocailMedia.controller;

import com.example.SocailMedia.dto.AuthorResponse;
import com.example.SocailMedia.dto.CreateAuthorRequest;
import com.example.SocailMedia.model.Author;
import com.example.SocailMedia.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Author getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

}
