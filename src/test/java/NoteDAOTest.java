import com.example.notetaker.model.NoteDAO;
import com.example.notetaker.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NoteDAOTest {

    private static final String TITLE = "Sample Title";
    private static final String CONTENT = "Sample content for testing.";
    private static final String UPDATED_TITLE = "Updated Title";
    private static final String UPDATED_CONTENT = "Updated content for testing.";

    private Note note;

    @BeforeEach
    public void setUp() {
        NoteDAO.initDatabase();
        note = new Note(TITLE, CONTENT);
        NoteDAO.saveNote(note);
    }

    @Test
    public void testSaveNote() {
        List<Note> notes = NoteDAO.getAllNotes();
        assertFalse(notes.isEmpty());
        Note retrievedNote = notes.get(notes.size() - 1);
        assertEquals(TITLE, retrievedNote.getTitle());
        assertEquals(CONTENT, retrievedNote.getContent());
    }

    @Test
    public void testUpdateNote() {
        List<Note> notes = NoteDAO.getAllNotes();
        Note noteToUpdate = notes.get(notes.size() - 1); // Get last inserted
        noteToUpdate.setTitle(UPDATED_TITLE);
        noteToUpdate.setContent(UPDATED_CONTENT);
        NoteDAO.updateNote(noteToUpdate);

        List<Note> updatedNotes = NoteDAO.getAllNotes();
        Note updatedNote = updatedNotes.stream()
                .filter(n -> n.getId() == noteToUpdate.getId())
                .findFirst()
                .orElse(null);

        assertNotNull(updatedNote);
        assertEquals(UPDATED_TITLE, updatedNote.getTitle());
        assertEquals(UPDATED_CONTENT, updatedNote.getContent());
    }

    @Test
    public void testGetAllNotes() {
        List<Note> notes = NoteDAO.getAllNotes();
        assertNotNull(notes);
        assertTrue(notes.size() > 0);
    }
}
