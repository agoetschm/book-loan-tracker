package com.example.spring_vue_graphql;

import java.util.Arrays;
import java.util.List;

public record Book(int id, String title, int pageCount, int authorId) {

  private static List<Book> books = Arrays.asList(
      new Book(1, "Effective Java", 416, 1),
      new Book(2, "Hitchhiker's Guide to the Galaxy", 208, 2),
      new Book(3, "Down Under", 436, 3),
      new Book(4, "Au bohnheur des ogres", 1, 4),
      new Book(5, "Monsieur Malaussène", 1, 4),
      new Book(6, "Dune", 1, 5),
      new Book(7, "Donne in viaggio", 1, 6),
      new Book(8, "Harry Potter and the Philosopher's Stone", 1, 7),
      new Book(9, "The Lies of Locke Lamora", 1, 8),
      new Book(10, "Warbreaker", 1, 9),
      new Book(11, "Mistborn", 1, 9),
      new Book(12, "Barbarian Days: A Surfing Life", 1, 10)
  );

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
