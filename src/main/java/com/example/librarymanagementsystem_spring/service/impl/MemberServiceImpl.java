package com.example.librarymanagementsystem_spring.service.impl;

import com.example.librarymanagementsystem_spring.model.Book;
import com.example.librarymanagementsystem_spring.model.Member;
import com.example.librarymanagementsystem_spring.repository.BookRepository;
import com.example.librarymanagementsystem_spring.repository.MemberRepository;
import com.example.librarymanagementsystem_spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Integer id) {
        return memberRepository.findByIdWithBooks(id).orElse(null);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteById(Integer id) {
        memberRepository.deleteById(id);
    }

    @Override
    public boolean borrowBook(Integer memberId, Integer bookId) {
        Member member = memberRepository.findByIdWithBooks(memberId).orElse(null);
        if (member == null) {
            return false;
        }
        if (bookId == null) {
            return false;
        }

        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            return false;
        }
        if (member.getBorrowedBooks().contains(book)) {
            return false;
        }
        member.getBorrowedBooks().add(book);
        memberRepository.save(member);
        return true;
    }


    @Override
    public void returnBook(Integer memberId, Integer bookId) {
        Member member = memberRepository.findByIdWithBooks(memberId).orElse(null);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        member.getBorrowedBooks().remove(book);
        memberRepository.save(member);
    }
}
