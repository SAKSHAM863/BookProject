package com.Project3.Books.Implementations;

import com.Project3.Books.Author;
import com.Project3.Books.Repository.AuthorRepo;
import com.Project3.Books.Repository.AuthorRepository;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import com.mongodb.client.AggregateIterable;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorImpl implements AuthorRepository {
    @Autowired
    MongoClient mongoClient;
    @Autowired
    MongoConverter converter;

    @Override
    public List<Author> findbyName(String name) {
        List<Author>finalresult = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("Books");
        MongoCollection<Document> collection = database.getCollection("authors");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", name)
                                .append("path", "name")))));


        result.forEach(doc -> finalresult.add(converter.read(Author.class,doc)));

        return finalresult;
    }
}
