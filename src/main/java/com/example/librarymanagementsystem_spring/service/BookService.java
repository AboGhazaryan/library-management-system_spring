package com.example.librarymanagementsystem_spring.service;

import com.example.librarymanagementsystem_spring.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book save(Book book);

    List<Book> findAvailableBooks();

    List<Book> searchAvailableBooks(String keyword);

    boolean deleteBookById(Integer id);

    List<Book> searchBooks(String keyword);

    List<Book> findByCategoryId(Integer categoryId);

    List<Book> searchBookAndFilter(String keyword,Integer categoryId);
}
