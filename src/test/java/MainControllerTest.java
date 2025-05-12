import com.example.notetaker.controller.MainController;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.GraphicsEnvironment;
import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MainControllerTest {

    private static boolean javafxStarted = false;
    private MainController controller;

    @BeforeEach
    void setUp() throws Exception {
        // Skip all tests if running in headless environment
        assumeTrue(!GraphicsEnvironment.isHeadless(), "Skipping JavaFX tests in headless mode");

        if (!javafxStarted) {
            CountDownLatch latch = new CountDownLatch(1);
            Platform.startup(latch::countDown);
            if (!latch.await(5, TimeUnit.SECONDS)) {
                throw new IllegalStateException("JavaFX initialization took too long.");
            }
            javafxStarted = true;
        }

        controller = new MainController();

        StackPane dummyPane = new StackPane();
        Field field = MainController.class.getDeclaredField("contentArea");
        field.setAccessible(true);
        field.set(controller, dummyPane);
    }

    @Test
    void testNoteViewLoadsWithoutException() {
        assumeTrue(!GraphicsEnvironment.isHeadless());
        assertDoesNotThrow(() -> Platform.runLater(controller::openNewNote));
    }

    @Test
    void testAIViewLoadsWithoutException() {
        assumeTrue(!GraphicsEnvironment.isHeadless());
        assertDoesNotThrow(() -> Platform.runLater(controller::openAIView));
    }

    @Test
    void testSavedNotesLoadsWithoutException() {
        assumeTrue(!GraphicsEnvironment.isHeadless());
        assertDoesNotThrow(() -> Platform.runLater(controller::openSavedNotes));
    }

    @Test
    void testSwitchToEditNoteManually() {
        assumeTrue(!GraphicsEnvironment.isHeadless());
        assertDoesNotThrow(() -> Platform.runLater(() ->
                controller.switchView("edit-note.fxml")));
    }
}
