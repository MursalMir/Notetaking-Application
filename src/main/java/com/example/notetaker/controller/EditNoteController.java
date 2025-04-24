package com.example.notetaker.controller;

import com.example.notetaker.model.Note;
import com.example.notetaker.model.NoteDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditNoteController {
    private Note currentNote;

    @FXML private TextField titleField;
    @FXML private TextArea contentArea;

    public void setNote(Note note) {
        this.currentNote = note;
        titleField.setText(note.getTitle());
        contentArea.setText(note.getContent());
    }

    @FXML
    public void updateNote() {
        currentNote.setTitle(titleField.getText());
        currentNote.setContent(contentArea.getText());
        NoteDAO.updateNote(currentNote);  // You'll need to add this method

        returnToHome();
    }

    @FXML
    public void returnToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/notetaker/saved-notes.fxml"));
            Stage stage = (Stage) titleField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}