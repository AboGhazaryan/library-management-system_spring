package com.example.librarymanagementsystem_spring.repository;

import com.example.librarymanagementsystem_spring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("""
            select b from Book b where b not in (
                select bb from Member m join m.borrowedBooks bb )
            """)
    List<Book> findAvailableBooks();

    @Query("""
        select b from Book b
        where b not in (
            select bb from Member m join m.borrowedBooks bb
        )
        and (
            lower(b.title) like lower(concat(:keyword, '%'))
            or lower(b.author.name) like lower(concat(:keyword, '%'))
        )
    """)
    List<Book> searchAvailableBooks(@Param("keyword") String keyword);

    @Query("""
        SELECT b FROM Book b
        WHERE
            LOWER(b.title) LIKE LOWER(CONCAT(:keyword, '%'))
            OR LOWER(b.author.name) LIKE LOWER(CONCAT(:keyword, '%'))
            OR LOWER(b.author.surname) LIKE LOWER(CONCAT(:keyword, '%'))
    """)
    List<Book> searchBooks(@Param("keyword") String keyword);


    List<Book> findByCategoryId(Integer categoryId);

    @Query("""
        SELECT b FROM Book b
        WHERE
            (
                LOWER(b.title) LIKE LOWER(CONCAT(:keyword, '%'))
                OR LOWER(b.author.name) LIKE LOWER(CONCAT(:keyword, '%'))
                OR LOWER(b.author.surname) LIKE LOWER(CONCAT(:keyword, '%'))
            )
            AND b.category.id = :categoryId
    """)
    List<Book> searchBooksByKeywordAndCategory(@Param("keyword") String keyword,
                                     @Param("categoryId") Integer categoryId);

}
