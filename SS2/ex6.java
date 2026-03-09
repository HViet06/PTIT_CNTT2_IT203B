package SS2;

@FunctionalInterface
interface UserProcessor {
    String process(User4 u);
}
class User4 {
    private String username;

    public User4(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}
class UserUtils {
    public static String convertToUpperCase(User4 u) {
        return u.getUsername().toUpperCase();
    }
}

public class ex6 {
    public static void main(String[] args) {
        UserProcessor processor = UserUtils::convertToUpperCase;
        User4 user = new User4("adawds");
        String result = processor.process(user);
        System.out.println(result);
    }
}