package com.Project3.Books.Repository;

import com.Project3.Books.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthorRepository{
    List<Author> findbyName(String name);
}
