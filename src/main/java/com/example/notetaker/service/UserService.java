package com.example.notetaker.service;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static UserService instance = null;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        return com.example.notetaker.model.UserDAO.getUserByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    public boolean register(String username, String password) {
        if (username == null || username.isBlank() ||
                password == null || password.isBlank()) {
            return false;
        }

        return com.example.notetaker.model.UserDAO.registerUser(
                new com.example.notetaker.model.User(username, password)
        );
    }
}
