package com.example.notetaker.model;

public class Note {
    private int id;
    private String title;
    private String content;

    // Default constructor (needed for some frameworks or TableView binding)
    public Note() {
    }

    // Constructor without ID (used when creating a new note)
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Constructor with ID (used when retrieving from DB)
    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Optional: toString() for easy debugging or displaying in UI
    @Override
    public String toString() {
        return title; // or return title + ": " + content;
    }
}
