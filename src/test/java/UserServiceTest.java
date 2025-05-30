import com.example.notetaker.model.User;
import com.example.notetaker.model.UserDAO;
import com.example.notetaker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.notetaker.model.UserDAO;
import org.junit.jupiter.api.AfterEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService service;

    @BeforeEach
    void setUp() {
        service = UserService.getInstance();
        cleanUp();
        clearUsersTable();  // 💡 Reset the DB before each test
    }

    private void clearUsersTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:users.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM users");  // ✅ Removes all users before each test
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRegisterNewUser() {
        boolean result = service.register("testuser", "password123");
        assertTrue(result, "User should register successfully");
    }

    @Test
    void testRegisterBlankFields() {
        assertFalse(service.register("", "password"));
        assertFalse(service.register("username", ""));
        assertFalse(service.register("", ""));
        assertFalse(service.register("   ", "   "));
    }


    @Test
    void testRegisterDuplicateUser() {
        service.register("dupeuser", "pass1");
        boolean result = service.register("dupeuser", "pass2");
        assertFalse(result, "Should not allow duplicate usernames");
    }

    @Test
    void testLoginValidCredentials() {
        service.register("validuser", "correctpass");
        boolean result = service.login("validuser", "correctpass");
        assertTrue(result, "Should allow login with correct credentials");
    }

    @Test
    void testLoginWrongPassword() {
        service.register("loginuser", "rightpass");
        boolean result = service.login("loginuser", "wrongpass");
        assertFalse(result, "Should reject incorrect password");
    }

    @Test
    void testLoginNonExistentUser() {
        boolean result = service.login("ghostuser", "any");
        assertFalse(result, "Should fail login for unknown user");
    }

    @AfterEach
    void cleanUp() {
        UserDAO.deleteUser("testuser");
        UserDAO.deleteUser("dupeuser");
        UserDAO.deleteUser("validuser");
        UserDAO.deleteUser("loginuser");
        UserDAO.deleteUser("ghostuser");
    }
}
