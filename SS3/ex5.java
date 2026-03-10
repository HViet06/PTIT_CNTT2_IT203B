package SS3;

import java.util.*;

public class ex5 {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alexander", "a@gmail.com", "ACTIVE"),
                new User("bob", "b@gmail.com", "ACTIVE"),
                new User("charlotte", "c@gmail.com", "ACTIVE"),
                new User("david", "d@gmail.com", "ACTIVE"),
                new User("Benjamin", "e@gmail.com", "ACTIVE"));
        users.stream().sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed()).limit(3).map(User::username).forEach(System.out::println);
    }
}