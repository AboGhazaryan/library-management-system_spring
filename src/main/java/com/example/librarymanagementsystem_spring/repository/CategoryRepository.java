package com.example.librarymanagementsystem_spring.repository;

import com.example.librarymanagementsystem_spring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> { }
