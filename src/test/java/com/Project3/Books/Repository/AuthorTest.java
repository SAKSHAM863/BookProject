package com.Project3.Books.Repository;

import com.Project3.Books.Address;
import com.Project3.Books.Author;
import com.Project3.Books.Book;
import com.Project3.Books.Service.AuthorService;
import com.Project3.Books.Service.BooksService;
import javafx.beans.binding.When;
import javafx.beans.value.ObservableBooleanValue;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class AuthorTest {


    @Autowired
    private BooksService booksService;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    Bookrepo bookrepo;


    @MockBean
    AuthorRepo authorRepo;
    @MockBean
    AuthorRepository authorRepository;
    @Autowired
    AuthorService authorService;
    @Test
    public void getAuthorsAll(){
        Address address = new Address(123,"Delhi","New Delhi");
        when(authorRepo.findAll()).thenReturn( Stream.of(new Author("1","Saksham",address)).collect(Collectors.toList()));
        assertEquals(1,authorService.findAll().size());
//        List<Author> authorList = authorRepository.findAll();
//        Assertions.assertFalse(authorList.isEmpty());
    }
    @Test
    public void getAuthorsByName(){
        Address address = new Address(123,"Delhi","New Delhi");
        when(authorRepository.findbyName("Neeraj")).thenReturn(Stream.of(new Author("1","Neeraj",address)).collect(Collectors.toList()));
        assertEquals("1",(authorService.getAuthorsByNameService("Neeraj")).get(0).getId());
    }

    @Test
    public void getAllBooks(){
        when(bookrepo.findAll()).thenReturn(Stream.of(new Book("121",5,"1","Fiction"),new Book("122",8,"2","Horror")).collect(Collectors.toList()));
        assertEquals(2,(booksService.findAllService().size()));

    }
    @Test
    public void getBooksByAuthorName(){
        when(bookRepository.findbyAuthorID("Neeraj")).thenReturn(Stream.of(new Book("121",5,"1","Fiction"),new Book("122",8,"2","Horror")).collect(Collectors.toList()));
        assertEquals("Fiction",(booksService.getByAuthorIDService("Neeraj").get(0).getGenre()));
    }
    @Test
    public void getBookByGenre(){
        when(bookRepository.findbyGenre("Fiction")).thenReturn(Stream.of(new Book("121",5,"1","Fiction"),new Book("122",8,"2","Fiction")).collect(Collectors.toList()));
        assertEquals("121",(booksService.getBookByGenreService("Fiction").get(0).getId()));
    }
    @Test
   public void insertBook(){
        Book book = new Book("121",5,"1","Biology");
        when(bookrepo.insert(book)).thenReturn(book);
        assertEquals("121",booksService.createBookService(book).getId());
   }
   @Test
    public void insertAuthor(){
       Address address = new Address(123,"Delhi","New Delhi");
       Author author = new Author("123","Saksham",address);
       assertEquals("123",authorService.createAuthorService(author).getId());
   }


}
