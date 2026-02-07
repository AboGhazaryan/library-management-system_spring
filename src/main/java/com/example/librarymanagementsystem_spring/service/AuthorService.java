package com.example.librarymanagementsystem_spring.service;

import com.example.librarymanagementsystem_spring.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author save(Author author);

    boolean deleteAuthorById(Integer id);
}
