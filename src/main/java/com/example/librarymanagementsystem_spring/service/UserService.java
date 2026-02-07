package com.example.librarymanagementsystem_spring.service;

import com.example.librarymanagementsystem_spring.model.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findByUsername(String username);
}
