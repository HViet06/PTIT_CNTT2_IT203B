package SS2;

import java.util.*;
import java.util.function.*;

class User3 {
    private String username;

    public User3() {
        this.username = "guest";
    }

    public User3(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

public class ex4 {
    public static void main(String[] args) {

        // Danh sách users
        List<User3> users = new ArrayList<>();
        users.add(new User3("admin"));
        users.add(new User3("john"));
        users.add(new User3("anna"));

        // 1. (user) -> user.getUsername()
        Function<User3, String> getUsername = User3::getUsername;

        // 2. (s) -> System.out.println(s)
        Consumer<String> print = System.out::println;

        // 3. () -> new User()
        Supplier<User3> createUser = User3::new;

        // Áp dụng
        for (User3 u : users) {
            print.accept(getUsername.apply(u));
        }

        // Tạo user mới
        User3 newUser = createUser.get();
        print.accept("New user: " + newUser.getUsername());
    }
}