package com.example.librarymanagementsystem_spring.repository;

import com.example.librarymanagementsystem_spring.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("""
    select distinct m from Member m
    left join fetch m.borrowedBooks b
    left join fetch b.author
    left join fetch b.category
    where m.id = :id
""")
    Optional<Member> findByIdWithBooks(@Param("id") Integer id);
}
