package com.example.librarymanagementsystem_spring.service;

import com.example.librarymanagementsystem_spring.model.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAll();

    Member findById(Integer id);

    Member save(Member member);

    void deleteById(Integer id);

    boolean borrowBook(Integer memberId, Integer bookId);

    void returnBook(Integer memberId,Integer bookId);


}
