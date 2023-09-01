package com.Project3.Books.Service;

import com.Project3.Books.Author;
import com.Project3.Books.Repository.AuthorRepo;
import com.Project3.Books.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorRepo authorRepo;
    public List<Author> findAll(){
        return authorRepo.findAll();
    }
    public List<Author> getAuthorsByNameService(String name) {

        return authorRepository.findbyName(name);
    }
    public Author createAuthorService(Author  author){

         authorRepo.insert(author);
         return author;
    }

}
