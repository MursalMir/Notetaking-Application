package com.example.notetaker.controller;

import com.example.notetaker.service.OllamaConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.net.http.HttpClient;

/**
 * Placeholder GUI window - functionality needs to be moved to the appropriate elements.
 */
public class PlaceholderNoteTakingWindowController {
    @FXML
    private TextArea noteEntryArea;

    @FXML
    private TextArea expandedNoteArea;

    OllamaConnection ollamaConnection = new OllamaConnection();

    public PlaceholderNoteTakingWindowController() {

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
