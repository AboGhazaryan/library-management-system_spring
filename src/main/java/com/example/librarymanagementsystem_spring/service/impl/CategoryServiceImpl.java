package com.example.librarymanagementsystem_spring.service.impl;

import com.example.librarymanagementsystem_spring.model.Category;
import com.example.librarymanagementsystem_spring.repository.CategoryRepository;
import com.example.librarymanagementsystem_spring.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }


    @Override
    public boolean deleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return false;
        }
        if (!category.getBooks().isEmpty()) {
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }


}
