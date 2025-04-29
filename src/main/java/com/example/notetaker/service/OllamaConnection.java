package com.example.notetaker.service;

import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class to contain data and methods related to interacting with Ollama and the selected LLM
 */
public class OllamaConnection {
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";
    private static final String MODEL_NAME = "gemma3";
    public static final String PROMPT_PREFIX = "Expand the following sparse study notes into clear, structured, brief, and concise study notes. "
            + "Use headings, dot points, and explanations where needed. Include only these notes in your "
            + "response - do not add introductions, conclusions or questions. Only output the improved notes "
            + "as the response will be saved as a page of notes to be studied. \n\nNotes:\n";


    private final HttpClient httpClient = HttpClient.newHttpClient();


    /**
     * Converts user-written notes into improved, expanded, AI-generated study notes
     * @param userInput Brief user-written study notes
     * @return AI-Generated study notes. Returns an error if there is no response field in the JSON response
     */
    public String generateExpandedNotes(String userInput) {
        String prompt = generatePrompt(userInput);
        String requestBody = createRequestBody(prompt);
        JsonObject jsonResponse = sendRequest(requestBody);

        if (jsonResponse.has("response") && !jsonResponse.get("response").isJsonNull()) {
            return jsonResponse.get("response").getAsString();
        } else {
            System.out.println("Error: No response field found in JSON: " + jsonResponse);
            return "Error: No response received from AI.";
        }
    }

    /**
     * Creates a prompt to send to the AI requesting improved study notes
     * @param userInput The user-written study notes for the AI to improve
     * @return The full prompt string to be sent to the AI
     */
    public String generatePrompt(String userInput) {
        // Adds a string on to the beginning of each prompt send to the LLM.
        return PROMPT_PREFIX + userInput;
    }

    /**
     * Uses Gson to create a properly formatted HTTP request to send to Ollama
     * @param prompt The prompt to include in the request
     * @return A string containing a valid Ollama HTTP request
     */
    public String createRequestBody(String prompt) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("model", MODEL_NAME);
        jsonObject.addProperty("prompt", prompt);
        jsonObject.addProperty("stream", false);
        return new Gson().toJson(jsonObject);
    }

    /**
     * Sends an HTTP request to the Ollama API
     * @param requestBody A string to be added to the body of the HTTP request
     * @return A JsonObject containing the full response from the LLM, or an error JsonObject if the request failed.
     */
    public JsonObject sendRequest(String requestBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(OLLAMA_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();


            return JsonParser.parseString(responseBody).getAsJsonObject();

        } catch (Exception e) {
            System.out.println("Error receiving HTTP response: " + e.getMessage());

            // Return an error JsonObject
            JsonObject errorObj = new JsonObject();
            errorObj.addProperty("error", "Failed to connect to Ollama.");
            return errorObj;
        }
    }



}
