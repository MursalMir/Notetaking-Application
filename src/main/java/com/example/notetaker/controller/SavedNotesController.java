package com.example.notetaker.controller;

import com.example.notetaker.model.Note;
import com.example.notetaker.model.NoteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class SavedNotesController {

    @FXML
    private ListView<Note> notesListView;

    @FXML
    private TextField searchField;

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
    private void deleteSelectedNote() {
        Note selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            NoteDAO.deleteNote(selectedNote);
            notesListView.getItems().remove(selectedNote); // update the UI
        } else {
            showAlert("No Selection", "Please select a note to delete.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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

    public void searchNotes(ActionEvent actionEvent) {
        String keyword = searchField.getText().trim();
        List<Note> results = NoteDAO.searchNotesByContent(keyword);
        notesListView.getItems().setAll(results);
    }
}