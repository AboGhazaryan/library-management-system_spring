package com.example.librarymanagementsystem_spring.service.impl;

import com.example.librarymanagementsystem_spring.model.Author;
import com.example.librarymanagementsystem_spring.repository.AuthorRepository;
import com.example.librarymanagementsystem_spring.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return  authorRepository.findAll();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }


    @Override
    public boolean deleteAuthorById(Integer id) {
       Author author = authorRepository.findById(id).orElse(null);
       if (author == null) {
           return false;
       }
       if (!author.getBooks().isEmpty()) {
           return false;
       }
       authorRepository.delete(author);
       return true;
    }


}
