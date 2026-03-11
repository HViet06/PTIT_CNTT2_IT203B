package SS4v2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ex3 {
    static class UserProcessor {
        public String processEmail(String email) {
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Email phải chứa ký tự @");
            }
            String[] parts = email.split("@");
            if (parts.length != 2 || parts[1].isEmpty()) {
                throw new IllegalArgumentException("Email phải có tên miền hợp lệ");
            }
            return email.toLowerCase();
        }
    }
    private UserProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new UserProcessor();
    }
    @Test
    void validEmailShouldReturnSameLowercase() {

        String input = "user@gmail.com";

        String result = processor.processEmail(input);
        assertEquals("user@gmail.com", result);
    }

    @Test
    void emailWithoutAtShouldThrowException() {

        String input = "usergmail.com";

        assertThrows(IllegalArgumentException.class,
                () -> processor.processEmail(input));
    }
    @Test
    void emailWithoutDomainShouldThrowException() {
        String input = "user@";
        assertThrows(IllegalArgumentException.class,
                () -> processor.processEmail(input));
    }

    @Test
    void emailShouldBeNormalizedToLowercase() {
        String input = "Example@Gmail.com";
        String result = processor.processEmail(input);

        assertEquals("example@gmail.com", result);
    }
}
