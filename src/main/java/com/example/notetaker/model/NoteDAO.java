package com.example.notetaker.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {
    private static final String DB_URL = "jdbc:sqlite:notes.db";

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS notes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT NOT NULL," +
                    "content TEXT NOT NULL)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveNote(Note note) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO notes(title, content) VALUES(?, ?)")) {
            pstmt.setString(1, note.getTitle());
            pstmt.setString(2, note.getContent());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        Note note;
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM notes")) {
            while (rs.next()) {
                note = new Note(rs.getString("title"), rs.getString("content"));
                note.setId(rs.getInt("id"));
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;


    }
    public static void updateNote(Note note)
    {
        String sql = "UPDATE notes SET title = ?, content = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, note.getTitle());
            pstmt.setString(2, note.getContent());
            pstmt.setInt(3, note.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteNote(Note note) {
        String sql = "DELETE FROM notes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, note.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to search the Notes database for notes containing a string
     * @param searchWord The user input search string
     * @return An ArrayList of Note objects with their ID, title, and content.
     */
    public List<Note> searchNotesByContent(String searchWord) {
       List<Note> matchedNotes = new ArrayList<>();
       String sql = "SELECT * FROM notes WHERE content LIKE ?";

       try(Connection conn = DriverManager.getConnection(DB_URL);
           PreparedStatement statement = conn.prepareStatement(sql)) {

           statement.setString(1, "%" + searchWord + "%");
           ResultSet searchresults = statement.executeQuery();

           while (searchresults.next()) {
               int id = searchresults.getInt("id");
               String title = searchresults.getString("title");
               String content = searchresults.getString("content");

               matchedNotes.add(new Note(id, title, content));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

       return matchedNotes;

    }
}