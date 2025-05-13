package com.example.notetaker.model;

public class User {
    private String username;
    private String password;

    public static User currentUser = null;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
