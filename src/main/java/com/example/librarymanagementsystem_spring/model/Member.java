package com.example.librarymanagementsystem_spring.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "borrowedBooks")
@EqualsAndHashCode(exclude = "borrowedBooks")
@Entity
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fullname")
    private String fullName;
    private String email;
    private String phone;
    private LocalDate registrationDate;

    @ManyToMany
    @JoinTable(name ="member_books",joinColumns = @JoinColumn(name="member_id"),
    inverseJoinColumns = @JoinColumn(name="book_id"))
    private List<Book> borrowedBooks = new ArrayList<>();
}
