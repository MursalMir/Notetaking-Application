import com.example.notetaker.model.LearningMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LearningMaterialTest {
    private static final int ID = 5;
    private static final String FILENAME = "lecture_notes.txt";
    private static final String FILEPATH = "/path/example/lecture_notes.txt";

    private static final int NEW_ID = 10;
    private static final String NEW_FILENAME = "updated_notes.txt";
    private static final String NEW_FILEPATH = "/path/example/updated_notes.txt";

    private LearningMaterial material;

    @BeforeEach
    public void setUp() {
        material = new LearningMaterial(ID, FILENAME, FILEPATH);
    }

    @Test
    public void testGetId() {
        assertEquals(ID, material.getId());
    }

    @Test
    public void testGetFilename() {
        assertEquals(FILENAME, material.getFilename());
    }

    @Test
    public void testSetFilename() {
        material.setFilename(NEW_FILENAME);
        assertEquals(NEW_FILENAME, material.getFilename());
    }

    @Test
    public void 
}
