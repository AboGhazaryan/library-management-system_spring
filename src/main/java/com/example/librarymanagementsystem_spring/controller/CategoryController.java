package com.example.librarymanagementsystem_spring.controller;

import com.example.librarymanagementsystem_spring.model.Category;
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
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(ModelMap modelMap) {
        List<Category> categories = categoryService.findAll();
        modelMap.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/category/add")
    public String addCategory() {
        return "addCategory";
    }

    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @PostMapping("/category/delete")
    public String deleteCategory(@RequestParam Integer categoryId, RedirectAttributes redirectAttributes) {
        boolean deleted = categoryService.deleteCategoryById(categoryId);

        if (!deleted) {
             redirectAttributes.addFlashAttribute("errorMessage","The category cannot be deleted because it is already owned by a book");
        }else{
            redirectAttributes.addFlashAttribute("infoMessage","Category has been deleted successfully.");
        }
        return "redirect:/categories";
    }


}
