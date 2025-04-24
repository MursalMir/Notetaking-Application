package com.example.notetaker.controller;

import com.example.notetaker.model.Note;
import com.example.notetaker.model.NoteDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NoteController {

    @FXML private TextField titleField;
    @FXML private TextArea contentArea;
    @FXML private ListView<Note> noteList;

    @FXML
    public void initialize() {
        NoteDAO.initDatabase();
        noteList.getItems().addAll(NoteDAO.getAllNotes());
    }

    @FXML
    public void saveNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (!title.isEmpty() && !content.isEmpty()) {
            Note note = new Note(title, content);
            NoteDAO.saveNote(note);

            // Refresh list
            noteList.getItems().clear();
            noteList.getItems().addAll(NoteDAO.getAllNotes());

            // Clear fields
            titleField.clear();
            contentArea.clear();
        }
    }
}