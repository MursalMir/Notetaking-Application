package com.example.notetaker.controller;

import com.example.notetaker.service.OllamaConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class AIViewController {


    @FXML
    private Button backToDashboardButton;

    @FXML
    private TextArea noteEntryArea;

    @FXML
    private TextArea expandedNoteArea;

    OllamaConnection ollamaConnection = new OllamaConnection();

    @FXML
    public void returnToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/notetaker/MainView.fxml"));
            Stage stage = (Stage) backToDashboardButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     Event handler for the generate button
     */
    @FXML
    private void onGenerateButtonClick(ActionEvent event) {
        // Generate improved notes from the ones in the noteEntryArea and print them in the
        // expanded note area.
        expandedNoteArea.setText(ollamaConnection.generateExpandedNotes(noteEntryArea.getText()));
    }
}
