package com.example.librarymanagementsystem_spring.service;

import com.example.librarymanagementsystem_spring.model.Category;

import java.util.List;

public interface CategoryService  {

    List<Category> findAll();

    Category save(Category category);

    boolean deleteCategoryById(Integer id);




}
