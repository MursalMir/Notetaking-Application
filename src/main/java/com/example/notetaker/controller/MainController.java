package com.example.notetaker.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    public StackPane contentArea;  // fx:id must match the one in MainView.fxml

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