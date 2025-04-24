package com.example.ainotetaking.model;

public class Note {

    // Note fields
    private String rawText;
    private String expandedText;

    public Note(String rawText) {
        this.rawText = rawText;
    }

    /**
     * Returns the raw note text
     * @return The raw note text input by the user
     */
    public String getRawText() {
        return rawText;
    }

    /**
     * Returns the AI-expanded note text
     * @return the AI-expanded note text
     */
    public String getExpandedText() {
        return expandedText;
    }

    /**
     * Sets the expandedText field of the note
     * @param expandedText The string value to set the expandedText to
     */
    public void setExpandedText(String expandedText) {
        this.expandedText = expandedText;
    }
}
