package com.example.librarymanagementsystem_spring.controller;

import com.example.librarymanagementsystem_spring.model.Book;
import com.example.librarymanagementsystem_spring.service.AuthorService;
import com.example.librarymanagementsystem_spring.service.BookService;
import com.example.librarymanagementsystem_spring.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;


    @GetMapping("/books")
    public String books(ModelMap modelMap,@RequestParam(required =false) String keyword,
                        @RequestParam(required = false)Integer categoryId) {

        List<Book> books;

        if (keyword != null && !keyword.isBlank() && categoryId != null) {
            books = bookService.searchBookAndFilter(keyword, categoryId);

        } else if (keyword != null && !keyword.isBlank()) {
            books = bookService.searchBooks(keyword);
        } else if (categoryId != null) {
            books = bookService.findByCategoryId(categoryId);
        } else {
            books = bookService.findAll();
        }

        if (books.isEmpty()) {
            modelMap.addAttribute("infoMessage", "No Books Found");
        }

        modelMap.addAttribute("books",books);
        modelMap.addAttribute("keyword", keyword);
        modelMap.addAttribute("selectedCategory", categoryId);
        modelMap.addAttribute("categories", categoryService.findAll());
        return "books";
    }

    @GetMapping("/book/add")
    public String addBook(ModelMap modelMap) {
        modelMap.addAttribute("authors",authorService.findAll());
        modelMap.addAttribute("categories",categoryService.findAll());
        return "addBook";
    }

    @PostMapping("/book/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }


    @PostMapping("/book/delete")
    public String deleteBook(@RequestParam Integer bookId, RedirectAttributes redirectAttributes) {
        boolean deleted = bookService.deleteBookById(bookId);
        if(!deleted){
            redirectAttributes.addFlashAttribute("message","The book cannot be deleted because it is already owned by a member.");
        }else{
            redirectAttributes.addFlashAttribute("successMessage","Book has been deleted successfully.");
        }
        return "redirect:/books";
    }


}
