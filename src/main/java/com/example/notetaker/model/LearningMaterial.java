package com.example.notetaker.model;

/**
 * Represents the learning material uploaded by the user
 */
public class LearningMaterial {
    private int id;
    private String filename;
    private String filepath;

    // Constructor for JavaFX TableView if needed
    public LearningMaterial() {}

    /**
     * Constructor without ID for new materials before saving
     * it to the database - ID is not required
     */

    public LearningMaterial(String filename, String filepath) {
        this.filename = filename;
        this.filepath = filepath;
    }

    /**
     * Full constructor with ID used for retrieving data from DB
     *
     * @param id Unique database ID
     * @param filename Name of the file
     * @param filepath Path to locally stored file
     */
    public LearningMaterial(int id, String filename, String filepath) {
        this.id = id;
        this.filename = filename;
        this.filepath = filepath;
    }

    // ----- Getters -----
    public int getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getFilepath() {
        return filepath;
    }

    // ----- Setters -----

    public void setId(int id) {
        this.id = id;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * A readable string version of the object for debugging
     */
    @Override
    public String toString() {
        return filename + " (" + filepath + ")";
    }
}
