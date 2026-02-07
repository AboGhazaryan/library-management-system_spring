package com.example.librarymanagementsystem_spring.controller;

import com.example.librarymanagementsystem_spring.model.Book;
import com.example.librarymanagementsystem_spring.model.Member;
import com.example.librarymanagementsystem_spring.service.BookService;
import com.example.librarymanagementsystem_spring.service.MemberService;
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
public class MemberController {

    private final MemberService memberService;
    private final BookService bookService;

    @GetMapping("/members")
    public String members(ModelMap modelMap) {
        List<Member> members = memberService.findAll();
        modelMap.addAttribute("members", members);
        return "members";
    }


    @GetMapping("/member/add")
    public String addMember(ModelMap modelMap) {
        return "addMember";
    }


    @PostMapping("/member/add")
    public String addMember(@ModelAttribute Member member) {
        memberService.save(member);
        return "redirect:/members";
    }

    @GetMapping("/membersDetails")
    public String membersDetails(@RequestParam("id") Integer id,
                                 @RequestParam(required = false) String keyword, ModelMap modelMap) {
        Member member = memberService.findById(id);
        if(member == null) {
            modelMap.addAttribute("errorMessage", "Member not found");
            return "redirect:/members";
        }
        modelMap.addAttribute("member", member);

        if (keyword != null && !keyword.isBlank()) {
            List<Book> searchAvailableBooks = bookService.searchAvailableBooks(keyword);

            if (searchAvailableBooks.isEmpty()) {
                modelMap.addAttribute("infoMessage", "No available books found");
            }
            modelMap.addAttribute("keyword", keyword);
            modelMap.addAttribute("searchAvailableBooks", searchAvailableBooks);
        }

        List<Book> availableBooks = bookService.findAvailableBooks();
        modelMap.addAttribute("availableBooks", availableBooks);

        return "membersDetails";
    }

    @PostMapping("/memberBook/borrow")
    public String borrowBook(@RequestParam Integer memberId,
                             @RequestParam(required = false) Integer bookId, RedirectAttributes redirectAttributes) {

        boolean success = memberService.borrowBook(memberId, bookId);

        if (!success) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Please select a book"
                    );
        }else{
            redirectAttributes.addFlashAttribute("successMessage",
                    "Book borrowed successfully");
        }
        return "redirect:/membersDetails?id=" + memberId;
    }

    @PostMapping("/memberBook/delete")
    public String returnBook(@RequestParam Integer memberId,
                             @RequestParam Integer bookId) {
        memberService.returnBook(memberId, bookId);

        return "redirect:/membersDetails?id=" + memberId;

    }

    @PostMapping("/member/delete")
    public String deleteMember(@RequestParam Integer memberId) {
        memberService.deleteById(memberId);
        return "redirect:/members";
    }
}

