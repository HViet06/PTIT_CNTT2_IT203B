package SS4v2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ProfileUpdateTest {
    static class User {
        String email;
        LocalDate birthDate;
        String name;

        User(String email, LocalDate birthDate, String name) {
            this.email = email;
            this.birthDate = birthDate;
            this.name = name;
        }
    }
    static class UserProfile {
        String email;
        LocalDate birthDate;
        String name;
        UserProfile(String email, LocalDate birthDate, String name) {
            this.email = email;
            this.birthDate = birthDate;
            this.name = name;
        }
    }
    public User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {

        if (newProfile.birthDate.isAfter(LocalDate.now())) {
            return null;
        }

        for (User u : allUsers) {
            if (u != existingUser && u.email.equals(newProfile.email)) {
                return null;
            }
        }

        existingUser.email = newProfile.email;
        existingUser.birthDate = newProfile.birthDate;
        existingUser.name = newProfile.name;

        return existingUser;
    }

    @Test
    void updateNormalProfile() {

        User user = new User("old@mail.com", LocalDate.of(2000,1,1),"A");

        UserProfile newProfile =
                new UserProfile("new@mail.com", LocalDate.of(2001,1,1),"B");

        List<User> users = new ArrayList<>();
        users.add(user);

        User result = updateProfile(user, newProfile, users);

        assertNotNull(result);
    }

    @Test
    void rejectFutureBirthDate() {

        User user = new User("mail@mail.com", LocalDate.of(2000,1,1),"A");

        UserProfile newProfile =
                new UserProfile("new@mail.com", LocalDate.now().plusDays(1),"B");

        List<User> users = new ArrayList<>();
        users.add(user);

        User result = updateProfile(user, newProfile, users);

        assertNull(result);
    }

    @Test
    void rejectDuplicateEmail() {

        User user1 = new User("a@mail.com", LocalDate.of(2000,1,1),"A");
        User user2 = new User("b@mail.com", LocalDate.of(2000,1,1),"B");

        UserProfile newProfile =
                new UserProfile("b@mail.com", LocalDate.of(2001,1,1),"A");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        User result = updateProfile(user1, newProfile, users);

        assertNull(result);
    }

    @Test
    void allowSameEmailUpdate() {

        User user = new User("mail@mail.com", LocalDate.of(2000,1,1),"A");

        UserProfile newProfile =
                new UserProfile("mail@mail.com", LocalDate.of(1999,1,1),"NewName");

        List<User> users = new ArrayList<>();
        users.add(user);

        User result = updateProfile(user, newProfile, users);

        assertNotNull(result);
    }

    @Test
    void updateWhenUserListEmpty() {

        User user = new User("mail@mail.com", LocalDate.of(2000,1,1),"A");

        UserProfile newProfile =
                new UserProfile("new@mail.com", LocalDate.of(1999,1,1),"B");

        List<User> users = new ArrayList<>();

        User result = updateProfile(user, newProfile, users);

        assertNotNull(result);
    }

    @Test
    void rejectMultipleViolations() {
        User user1 = new User("a@mail.com", LocalDate.of(2000,1,1),"A");
        User user2 = new User("b@mail.com", LocalDate.of(2000,1,1),"B");

        UserProfile newProfile =
                new UserProfile("b@mail.com", LocalDate.now().plusDays(5),"A");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        User result = updateProfile(user1, newProfile, users);

        assertNull(result);
    }
}