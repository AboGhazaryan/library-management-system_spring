package com.example.librarymanagementsystem_spring.controller;



import com.example.librarymanagementsystem_spring.model.User;
import com.example.librarymanagementsystem_spring.service.UserService;
import com.example.librarymanagementsystem_spring.service.securtity.SpringUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal SpringUser userDetails, ModelMap modelMap) {
        if (userDetails != null) {
            modelMap.addAttribute("user", userDetails.getUser());
        }
        return "home";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "loginPage";
    }

    @GetMapping("/registerPage")
    public String registerPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "registerPage";
    }

    @PostMapping("/register")
    public String  register(@ModelAttribute User user) {
        if(userService.findByUsername(user.getUsername()).isPresent()) {
            return "redirect:/register?msg=Username already exists!";
        }
        userService.save(user);
        return "redirect:/login?msg=Registered Successfully! Please login again!";
    }
}
