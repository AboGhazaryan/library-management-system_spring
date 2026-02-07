package com.example.librarymanagementsystem_spring.repository;

import com.example.librarymanagementsystem_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
