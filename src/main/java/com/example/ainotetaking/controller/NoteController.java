package com.example.ainotetaking.controller;

import com.example.ainotetaking.model.Note;
import com.example.ainotetaking.service.AIService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NoteController {
    @FXML
    private TextArea noteEntryArea;

    @FXML
    private TextArea expandedNoteArea;

    @FXML
    private Note currentNote;

    private AIService aiService = new AIService();

    /**
    Event handler for the generate button
     */
    @FXML
    private void onGenerateButtonClick(ActionEvent event) {
        String rawJson = aiService.generateExpandedNotes(noteEntryArea.getText());

        JsonObject jsonResponse = JsonParser.parseString(rawJson).getAsJsonObject();

        if (jsonResponse.has("response") && !jsonResponse.get("response").isJsonNull()) {

            String expandedText = jsonResponse.get("response").getAsString();
            expandedNoteArea.setText(expandedText);
        } else {
            // Debugging message
            System.out.println("Received JSON but no 'response' field: " + rawJson);

            expandedNoteArea.setText("Error: No response received from AI");
        }


    }


}
