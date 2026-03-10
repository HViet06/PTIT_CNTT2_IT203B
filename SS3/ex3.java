package SS3;

import java.util.*;
import java.util.Optional;
public class ex3 {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository();
        Optional<User> user = repo.findUserByUsername("alice");
        user.ifPresent(u -> System.out.println("Welcome " + u.username()));
        System.out.println(
                user.map(u -> "Welcome " + u.username())
                        .orElse("Guest login"));
    }
}