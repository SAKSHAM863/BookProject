package com.Project3.Books.Repository;

import com.Project3.Books.Book;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Bookrepo extends MongoRepository<Book, String> {


}
