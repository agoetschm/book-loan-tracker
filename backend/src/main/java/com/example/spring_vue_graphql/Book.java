package com.example.spring_vue_graphql;

import java.util.Arrays;
import java.util.List;

public record Book(int id, String title, int pageCount, int authorId) {

  private static List<Book> books = Arrays.asList(
      new Book(1, "Effective Java", 416, 1),
      new Book(2, "Hitchhiker's Guide to the Galaxy", 208, 2),
      new Book(3, "Down Under", 436, 3));

  public static Book getById(int id) {
    return books.stream()
        .filter(book -> book.id() == id)
        .findFirst()
        .orElse(null);
  }

  public static List<Book> getAll() {
    return books;
  }
}
