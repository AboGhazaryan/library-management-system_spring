package com.example.librarymanagementsystem_spring.repository;

import com.example.librarymanagementsystem_spring.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> { }
