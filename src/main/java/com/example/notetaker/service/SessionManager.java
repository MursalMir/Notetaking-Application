package com.example.notetaker.service;

public class SessionManager {
    private static SessionManager instance = null;
    private String currentUsername;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(String username) {
        this.currentUsername = username;
    }

    public void logout() {
        this.currentUsername = null;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public boolean isLoggedIn() {
        return currentUsername != null;
    }
}
