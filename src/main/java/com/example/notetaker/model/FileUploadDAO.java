package com.example.notetaker.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database operations for the uploaded learning materials
 * including creating the upload table and inserting new uploads.
 */

public class FileUploadDAO {
    // Uses the same database file as NoteDAO.java
    private static final String DB_URL = "jdbc:sqlite:notes.db";

    public static void createUploadTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS uploads (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                filename TEXT,
                filepath TEXT,
                uploaded_at DATETIME DEFAULT CURRENT_TIMESTAMP);
                """;
        // Open connecting to the SQLite DB
        try (Connection conn = DriverManager.getConnection(DB_URL);
             // Create statement to execute SQL query
            Statement stmt = conn.createStatement()) {

            // Run CREATE TABLE query
            stmt.execute(sql);
            // Print errors
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param filename Name of the uploaded file (material.pdf, lecture.txt etc.)
     * @param filepath Path where the file is stored locally
     */
    public static void insertUpload(String filename, String filepath) {
        // SQL insert query with placeholders
        String sql = "INSERT INTO uploads (filename, filepath) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the values for the prepared SQL statement
        pstmt.setString(1, filename);
        pstmt.setString(2, filepath);
        pstmt.executeUpdate(); // Insert new row into uploads table
        }
        // Print errors
        catch (SQLException e) {
        e.printStackTrace();
        }
    }

    /**
     * Retrieves all uploaded files from DB and returns them as LearningMaterial objects
     * @return List of LearningMaterial objects representing all file uploads in the DB
     */
    public static List<LearningMaterial> getAllUploads() {
        List<LearningMaterial> uploads = new ArrayList<>();

        // Select all rows from the created uploads table
        String sql = "SELECT * FROM uploads";

        try (Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            //Execute the query and store the results
            ResultSet rs = stmt.executeQuery(sql)) {

            // Iterate through each row in the result set
            while (rs.next()) {
                // Convert each database row into a LearningMaterial object
                LearningMaterial material = new LearningMaterial(
                        rs.getInt("id"),
                        rs.getString("filename"),
                        rs.getString("filepath")
                );
                // Add object to the list
                uploads.add(material);
            }
            // Print errors
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return full list of uploaded files
        return uploads;
    }
}
