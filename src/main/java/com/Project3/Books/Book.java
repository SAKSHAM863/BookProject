package com.Project3.Books;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "books")

public class Book {
    String id;
    int copiesAvailable;
    String authorId;
    String genre;

    public Book() {
    }
    public Book(String id, int copiesAvailable, String authorId, String genre){
        this.id=id;
        this.copiesAvailable=copiesAvailable;
        this.authorId=authorId;
        this.genre=genre;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
