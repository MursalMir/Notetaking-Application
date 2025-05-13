package com.example.notetaker.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;


import java.io.IOException;

public class MainController {

    @FXML
    public StackPane contentArea;

    public void switchView(String fxmlFile) {
        try {
            System.out.println("Loading FXML: " + fxmlFile);
            Node view = FXMLLoader.load(getClass().getResource("/com/example/notetaker/" + fxmlFile));
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            System.err.println("Failed to load: " + fxmlFile);
            e.printStackTrace();
        }
    }
    @FXML
    private void handleLogout() {
        com.example.notetaker.model.User.currentUser = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/notetaker/Login.fxml"));
            Scene loginScene = new Scene(loader.load());
            Stage currentStage = (Stage) contentArea.getScene().getWindow();
            currentStage.setScene(loginScene);
            currentStage.setTitle("Login");
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void openNewNote() {
        switchView("note-view.fxml");
    }

    @FXML
    public void openSavedNotes() {
        switchView("saved-notes.fxml");
    }

    @FXML
    public void openAIView() {
        switchView("ai-view.fxml");
    }

}