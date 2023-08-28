package com.Project3.Books.Repository;

import com.Project3.Books.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findbyGenre(String genre);
    List<Book> findbyGenreandCopiesAvailableGT(String genre, int copies);
    List<Book> findbyAuthorID(String Id);


}
