package com.example.ainotetaking.service;

import com.google.gson.JsonObject;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class to contain data and methods related to interacting with Ollama and the selected LLM
 */
public class AIService {
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";
    private static final String MODEL_NAME = "gemma3";

    /**
     * A method to pass user-written notes to the LLM and return expanded, improved notes.
     * Probably needs to be refactored and split into several smaller methods.
     * @param prompt The user input to prompt the LLM with
     *               (e.g. the notes taken by a student that are to be improved by the LLM)
     * @return Returns the response from the LLM as a string
     */
    public String generateExpandedNotes(String prompt) {
        try {
            // Build JSON safely using Gson to avoid characters from user input breaking
            // the JSON object structure.
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("model", MODEL_NAME);
            jsonObject.addProperty("prompt", prompt);
            jsonObject.addProperty("stream", false);
            String requestBody = new Gson().toJson(jsonObject);

            // Create the HTTP request to send to the Ollama server
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(OLLAMA_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the HTTP request to the Ollama server & save the response JSON
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Return the body of the HTTP response
            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error connecting to Ollama.";
        }
    }
}
