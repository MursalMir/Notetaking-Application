package com.example.notetaker.controller;

import com.example.notetaker.model.Note;
import com.example.notetaker.model.NoteDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;

public class SavedNotesController {

    @FXML private ListView<Note> notesListView;

    @FXML
    public void initialize() {
        notesListView.getItems().addAll(NoteDAO.getAllNotes());
    }
    @FXML
    public void editSelectedNote() {
        Note selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/notetaker/edit-note.fxml"));
                Stage stage = (Stage) notesListView.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                EditNoteController controller = loader.getController();
                controller.setNote(selectedNote);
                stage.setScene(scene);
                stage.setTitle("Edit Note");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void returnToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/notetaker/MainView.fxml"));
            Stage stage = (Stage) notesListView.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}