package com.example.librarymanagementsystem_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MembersController {

    @GetMapping("/members")
    public String members(ModelMap modelMap) {
        return "members";
    }
}

