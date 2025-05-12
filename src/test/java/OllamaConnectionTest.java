import com.example.notetaker.service.OllamaConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.mockito.Mockito;

import java.net.http.HttpClient;

public class OllamaConnectionTest {


    HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
    OllamaConnection connection = Mockito.spy(new OllamaConnection());




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

}
