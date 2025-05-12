package com.example.notetaker.controller;

import com.example.notetaker.model.FileUploadDAO;
import com.example.notetaker.service.OllamaConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
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
    public void initialize() {
        // Making sure the uploads table exists before trying to save a file to it
        FileUploadDAO.createUploadTable();

        // Load existing materials from the uploads table
        for (com.example.notetaker.model.LearningMaterial material : FileUploadDAO.getAllUploads()) {
            uploadedMaterialsListView.getItems().add(material.getFilename());
        }
    }

    @FXML
    private ListView<String> uploadedMaterialsListView;

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

    /**
     *
     * @param event
     */
    @FXML
    private void handleUploadButtonClick(ActionEvent event) {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Select Learning Material");

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                com.example.notetaker.model.FileUploadDAO.insertUpload(
                        selectedFile.getName(),
                        selectedFile.getAbsolutePath());

                uploadedMaterialsListView.getItems().add(selectedFile.getName());

                System.out.println("File uploaded: " + selectedFile.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onPrintSelectedFileButtonClick(ActionEvent event) {
        String selectedFilename = uploadedMaterialsListView.getSelectionModel().getSelectedItem();

        if (selectedFilename != null) {
            try {
                for (com.example.notetaker.model.LearningMaterial material : FileUploadDAO.getAllUploads()) {
                    if (material.getFilename().equals(selectedFilename)) {
                        File file = new File(material.getFilepath());

                        String learningMaterialText = com.example.notetaker.model.FileParser.extractText(file);

                        // Get the student notes from the input area
                        String studentNotes = noteEntryArea.getText();

                        // Combine into a prompt
                        String prompt = learningMaterialText + "\n\nUse the above learning material to improve the following student notes:\n\n" + studentNotes;

                        // Ai generated summary
                        String result = ollamaConnection.generateExpandedNotes(prompt);

                        // Display result
                        expandedNoteArea.setText(result);
                        return;
                    }
                }
                System.out.println("File not found in database - please reupload");
            } catch (Exception e) {
                e.printStackTrace();
                noteEntryArea.setText("Error reading file: " + e.getMessage());
            }
        }
        else {
            noteEntryArea.setText("Please select a file from the list");
        }

    }

}
