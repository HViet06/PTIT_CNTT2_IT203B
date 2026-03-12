package SS4v2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class RoleAccessTest {
    enum Role {
        ADMIN, MODERATOR, USER
    }
    enum Action {
        DELETE_USER, LOCK_USER, VIEW_PROFILE
    }

    static class User {
        String name;
        Role role;

        User(String name, Role role) {
            this.name = name;
            this.role = role;
        }
    }

    User admin = new User("admin", Role.ADMIN);
    User moderator = new User("mod", Role.MODERATOR);
    User user = new User("user", Role.USER);
    public boolean canPerformAction(User user, Action action) {

        switch (user.role) {
            case ADMIN:
                return true;
            case MODERATOR:
                if (action == Action.DELETE_USER) return false;
                return true;

            case USER:
                return action == Action.VIEW_PROFILE;

            default:
                return false;
        }
    }
    @Test
    void testAdminPermissions() {
        assertAll(
                () -> assertTrue(canPerformAction(admin, Action.DELETE_USER)),
                () -> assertTrue(canPerformAction(admin, Action.LOCK_USER)),
                () -> assertTrue(canPerformAction(admin, Action.VIEW_PROFILE))
        );
    }
    @Test
    void testModeratorPermissions() {
        assertAll(
                () -> assertFalse(canPerformAction(moderator, Action.DELETE_USER)),
                () -> assertTrue(canPerformAction(moderator, Action.LOCK_USER)),
                () -> assertTrue(canPerformAction(moderator, Action.VIEW_PROFILE))
        );
    }
    @Test
    void testUserPermissions() {
        assertAll(
                () -> assertFalse(canPerformAction(user, Action.DELETE_USER)),
                () -> assertFalse(canPerformAction(user, Action.LOCK_USER)),
                () -> assertTrue(canPerformAction(user, Action.VIEW_PROFILE))
        );
    }
    @AfterEach
    void cleanup() {
        admin = null;
        moderator = null;
        user = null;
    }
}