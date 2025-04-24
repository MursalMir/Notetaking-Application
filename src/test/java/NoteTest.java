import com.example.notetaker.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {
    private static final int ID = 13;
    private static final String TITLE = "TEST NOTE TITLE";
    private static final String CONTENT = "Test content here. Adding a few more words to it :)";

    private static final int NEW_ID = 2;
    private static final String NEW_TITLE = "New Note Title";
    private static final String NEW_CONTENT = "New test content here.";

    private Note note;

    @BeforeEach
    public void setUp() {
        note = new Note(ID, TITLE, CONTENT);
    }

    @Test
    public void testSetID() {
        note.setId(NEW_ID);
        assertEquals(NEW_ID, note.getId());
    }

    @Test
    public void testGetID() {
        assertEquals(ID, note.getId());
    }

    @Test
    public void testSetTitle() {
        note.setTitle(NEW_TITLE);
        assertEquals(NEW_TITLE, note.getTitle());
    }

    @Test
    public void testGetTitle() {
        assertEquals(TITLE, note.getTitle());
    }

    @Test
    public void testSetContent() {
        note.setContent(NEW_CONTENT);
        assertEquals(NEW_CONTENT, note.getContent());
    }

    @Test
    public void testGetContent() {
        assertEquals(CONTENT, note.getContent());
    }

}
