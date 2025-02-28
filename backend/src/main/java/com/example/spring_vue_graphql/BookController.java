package com.example.spring_vue_graphql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    // @QueryMapping
    // public Book bookById(@Argument int id) {
    //     return Book.getById(id);
    // }

    @QueryMapping
    public List<Book> books() {
        return Book.getAll();
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }
}
