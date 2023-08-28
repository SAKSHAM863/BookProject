package com.Project3.Books.Implementations;

import com.Project3.Books.Author;
import com.Project3.Books.Book;
import com.Project3.Books.Repository.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;

import com.mongodb.client.MongoClient;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import com.mongodb.client.AggregateIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BookImpl implements BookRepository {
    @Autowired
    MongoClient mongoClient;
    @Autowired
    MongoConverter converter;
    @Override
    public List<Book> findbyGenre(String gen) {
        List<Book>resultByGenre = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("Books");
        MongoCollection<Document> collection = database.getCollection("books");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", gen)
                                .append("path", "genre")))));

         result.forEach(doc -> resultByGenre.add(converter.read(Book.class,doc)));
         return resultByGenre;
    }

    @Override
    public List<Book> findbyGenreandCopiesAvailableGT(String gen, int copies) {
        List<Book>result2 = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("Books");
        MongoCollection<Document> collection = database.getCollection("books");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", gen)
                                .append("path", "genre")))));


        result.forEach(doc -> result2.add(converter.read(Book.class,doc)));

        List<Book> genreAndCopiesresult = new ArrayList<>();
        for(Book book: result2){
            if(book.getCopiesAvailable()>=copies)genreAndCopiesresult.add(book);
        }
        return genreAndCopiesresult;
    }

    @Override
    public List<Book> findbyAuthorID(String Id) {
        List<Book> finalAnswer= new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("Books");
        MongoCollection<Document> collection = database.getCollection("authors");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", Id)
                                .append("path", "name")))));
        List<Author> authorlist = new ArrayList<>();
//        for(Document d: result){
//            authorlist.add(converter.read(Author.class,d));
//        }
        result.forEach(doc -> authorlist.add(converter.read(Author.class,doc)));
        String[] authorId= new String[authorlist.size()];
        for(int i=0;i<authorlist.size();i++){
            authorId[i] = authorlist.get(i).getId();
        }
        finalAnswer=getBooksbyAuthor(authorId);
        return finalAnswer;

    }
    private List<Book> getBooksbyAuthor(String[] authorList){
        List<Book> finalResult = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("Books");
        MongoCollection<Document> collection = database.getCollection("books");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", Arrays.asList(authorList))
                                .append("path", "authorId")))));

//        for(Document D: result){
//            finalResult.add(converter.read(Book.class,D));
//        }
        result.forEach(doc -> finalResult.add(converter.read(Book.class,doc)));
        return finalResult;
    }
}
