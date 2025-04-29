import com.example.notetaker.service.OllamaConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.*;

public class OllamaConnectionTest {


    private final String TEST_PROMPT = "Test prompt here.";
    HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
    OllamaConnection connection = Mockito.spy(new OllamaConnection());



    @Test
    public void testExpandedNotesUserInputIsNullGivesCorrectError() {
        String expandedNotesResponse = connection.generateExpandedNotes(null);
        assertEquals(OllamaConnection.ERR_NO_INPUT, expandedNotesResponse);

    }

    @Test
    public void testGeneratePromptIncludesUserInput() {
        String input = "polymorphism, inheritance";
        String prompt = connection.generatePrompt(input);

        assertTrue(prompt.contains(input));
        assertTrue(prompt.contains(OllamaConnection.PROMPT_PREFIX));
    }

    @Test
    public void testCreateRequestBodyIncludesCorrectFields() {
        String prompt = "Sample prompt here";
        String requestBody = connection.createRequestBody(prompt);

        JsonObject parsed = JsonParser.parseString(requestBody).getAsJsonObject();
        assertEquals("gemma3", parsed.get("model").getAsString());
        assertEquals(prompt, parsed.get("prompt").getAsString());
        assertFalse(parsed.get("stream").getAsBoolean());
    }

    @Test
    public void testCreateRequestBodyWithValidPrompt() {
        String requestBody = connection.createRequestBody(TEST_PROMPT);

        assertTrue(requestBody.contains("\"model\":\"" + OllamaConnection.MODEL_NAME + "\""));
        assertTrue(requestBody.contains("\"prompt\":\"" + TEST_PROMPT + "\""));
        assertTrue(requestBody.contains("\"stream\":false"));

    }

}
