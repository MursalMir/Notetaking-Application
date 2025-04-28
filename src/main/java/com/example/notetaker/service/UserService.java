package com.example.notetaker.service;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static UserService instance = null;
    private Map<String, String> users;

    private UserService() {
        users = new HashMap<>();
        // Optional: add a default user for testing
        users.put("admin", "password");
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        users.put(username, password);
        return true;
    }
}
