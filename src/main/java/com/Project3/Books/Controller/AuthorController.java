package com.Project3.Books.Controller;

import com.Project3.Books.Author;
import com.Project3.Books.Repository.AuthorRepo;
import com.Project3.Books.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/authors")

public class AuthorController {
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public Author createAuthor(@RequestBody Author  author){
        return authorRepo.insert(author);
    }
    @GetMapping("/name")
    public List<Author> getAuthorsByName(@RequestBody String name){
            return authorRepository.findbyName(name);
    }
}
