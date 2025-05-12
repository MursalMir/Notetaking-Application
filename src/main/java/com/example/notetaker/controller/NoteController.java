package com.example.notetaker.controller;

import com.example.notetaker.Util.AlertUtils;
import com.example.notetaker.model.Note;
import com.example.notetaker.model.NoteDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class NoteController {

    @FXML private TextField titleField;
    @FXML private TextArea contentArea;

    @FXML
    public void initialize() {
        NoteDAO.initDatabase();
    }

    @FXML
    public void saveNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (!title.isEmpty() && !content.isEmpty()) {
            Note note = new Note(title, content);
            NoteDAO.saveNote(note);
            titleField.clear();
            contentArea.clear();
        } else {
            AlertUtils.showAlert("Missing Information", "Please fill in both the title and content fields before saving.");
        }
    }

    @FXML
    public void returnToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/notetaker/MainView.fxml"));
            Stage stage = (Stage) titleField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}