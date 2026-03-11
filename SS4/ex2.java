package SS4v2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ex2 {
    static class UserService {
        public boolean checkRegistrationAge(int age) {
            if (age < 0) {
                throw new IllegalArgumentException("Tuổi không được âm");
            }
            return age >= 18;
        }
    }

    private final UserService service = new UserService();

    @Test
    void TC01_validAge() {
        int input = 20;
        boolean result = service.checkRegistrationAge(input);
        assertEquals(true, result, "Tuổi >= 18 phải hợp lệ");
    }

    @Test
    void TC02_underAge() {
        int input = 16;
        boolean result = service.checkRegistrationAge(input);
        assertEquals(false, result, "Tuổi < 18 phải không hợp lệ");
    }

    @Test
    void TC03_negativeAgeThrowsException() {
        int input = -5;
        assertThrows(IllegalArgumentException.class,
                () -> service.checkRegistrationAge(input),
                "Tuổi âm phải ném ra IllegalArgumentException");
    }
}
