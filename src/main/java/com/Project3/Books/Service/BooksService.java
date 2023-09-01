package com.Project3.Books.Service;

import com.Project3.Books.Book;
import com.Project3.Books.Repository.BookRepository;
import com.Project3.Books.Repository.Bookrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class BooksService {
    @Autowired
    private Bookrepo bookrepo;
    @Autowired
    private BookRepository bookRepository;


    public List<Book> findAllService(){
        return bookrepo.findAll();
    }

    public List<Book> getBookByGenreService( String genre){
        return bookRepository.findbyGenre(genre);
    }

    public List<Book> getByAuthorIDService( String Id){
        return bookRepository.findbyAuthorID(Id);
    }

    public List<Book> getByGenreAndCopiesAvailService( String genre, int copies){
        return bookRepository.findbyGenreandCopiesAvailableGT(genre,copies);
    }

    public Book createBookService( Book book){
        bookrepo.save(book);
        return book;
    }



}
