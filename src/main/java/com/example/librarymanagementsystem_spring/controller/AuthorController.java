package com.example.librarymanagementsystem_spring.controller;

import com.example.librarymanagementsystem_spring.model.Author;
import com.example.librarymanagementsystem_spring.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;


    @GetMapping("/authors")
    public String author(ModelMap modelMap) {
        modelMap.addAttribute("authors", authorService.findAll());
        return "authors";
    }

    @GetMapping("/author/add")
    public String addAuthor() {
        return "addAuthor";
    }

    @PostMapping("/author/add")
    public String addAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }

    @PostMapping("/author/delete")
    public String deleteAuthor(@RequestParam Integer authorId, RedirectAttributes redirectAttributes) {
        boolean deleted = authorService.deleteAuthorById(authorId);
        if(!deleted){
            redirectAttributes.addFlashAttribute("Message","The author cannot be deleted because he/she has a book.");
        }else{
            redirectAttributes.addFlashAttribute("successMessage","Author has been deleted successfully.");
        }
        return "redirect:/authors";
    }





}
