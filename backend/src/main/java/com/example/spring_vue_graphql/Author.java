package com.example.spring_vue_graphql;


import java.util.Arrays;
import java.util.List;

public record Author (int id, String firstName, String lastName) {

    private static List<Author> authors = Arrays.asList(
        new Author(1, "Joshua", "Bloch"),
        new Author(2, "Douglas", "Adams"),
        new Author(3, "Bill", "Bryson"),
        new Author(4, "Daniel", "Pennac"),
        new Author(5, "Frank", "Herbert"),
        new Author(6, "Lucie", "Azema"),
        new Author(7, "J.K.", "Rowling"),
        new Author(8, "Scott", "Lynch"),
        new Author(9, "Brandon", "Sanderson"),
        new Author(10, "William", "Finnegan")
    );

    public static Author getById(int id) {
        return authors.stream()
				.filter(author -> author.id() == id)
				.findFirst()
				.orElse(null);
    }
}