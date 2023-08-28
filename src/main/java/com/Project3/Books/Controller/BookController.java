package com.Project3.Books.Controller;

import com.Project3.Books.Book;
import com.Project3.Books.Repository.BookRepository;
import com.Project3.Books.Repository.Bookrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
   @Autowired
    Bookrepo bookrepo;
   @Autowired
    BookRepository bookRepository;
    @GetMapping

    public List<Book> findAll(){
        return bookrepo.findAll();
    }
    @GetMapping("/genre")
    public List<Book> getBookByGenre(@RequestBody String genre){
        return bookRepository.findbyGenre(genre);
    }
    @GetMapping("/byName")
    public List<Book> getByAuthorID(@RequestBody String Id){
        return bookRepository.findbyAuthorID(Id);
   }
    @GetMapping("/{genre}/{copies}")
    public List<Book> getByGenreAndCopiesAvail(@PathVariable String genre, @PathVariable int copies){
        return bookRepository.findbyGenreandCopiesAvailableGT(genre,copies);
    }
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookrepo.save(book);
    }
}
