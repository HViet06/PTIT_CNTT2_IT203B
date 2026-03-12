package SS4v2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PasswordTest {
    public static String evaluatePasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            return "Yếu";
        }
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");

        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            return "Mạnh";
        }
        if (hasUpper || hasLower || hasDigit || hasSpecial) {
            return "Trung bình";
        }
        return "Yếu";
    }
    @Test
    void testEvaluatePasswordStrength() {
        assertAll("Password Strength Tests",
                () -> assertEquals("Mạnh",
                        evaluatePasswordStrength("Abc123!@")), // TC01
                () -> assertEquals("Trung bình",
                        evaluatePasswordStrength("abc123!@")), // TC02
                () -> assertEquals("Trung bình",
                        evaluatePasswordStrength("ABC123!@")), // TC03
                () -> assertEquals("Trung bình",
                        evaluatePasswordStrength("Abcdef!@")), // TC04
                () -> assertEquals("Trung bình",
                        evaluatePasswordStrength("Abc12345")), // TC05
                () -> assertEquals("Yếu",
                        evaluatePasswordStrength("Ab1!")), // TC06
                () -> assertEquals("Yếu",
                        evaluatePasswordStrength("password")), // TC07
                () -> assertEquals("Yếu",
                        evaluatePasswordStrength("ABC12345")) // TC08
        );
    }
}