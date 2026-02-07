package com.example.librarymanagementsystem_spring.service.impl;

import com.example.librarymanagementsystem_spring.model.Book;
import com.example.librarymanagementsystem_spring.repository.BookRepository;
import com.example.librarymanagementsystem_spring.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAvailableBooks() {
        return bookRepository.findAvailableBooks();
    }

    @Override
    public List<Book> searchAvailableBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return bookRepository.findAvailableBooks();
        }
        return bookRepository.searchAvailableBooks(keyword);
    }

    @Override
    public boolean deleteBookById(Integer id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return false;
        }
        if (!book.getMembers().isEmpty()) {
            return false;
        }
        bookRepository.delete(book);
        return true;
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword);
    }

    @Override
    public List<Book> findByCategoryId(Integer categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Book> searchBookAndFilter(String keyword, Integer categoryId) {
        if (keyword != null && !keyword.isBlank() && categoryId != null) {
            return bookRepository.searchBooksByKeywordAndCategory(keyword, categoryId);
        }
        if(keyword !=null && !keyword.isBlank()){
            return bookRepository.searchBooks(keyword);
        }

        if (categoryId != null) {
            return bookRepository.findByCategoryId(categoryId);
        }
        return bookRepository.findAll();
    }


}
