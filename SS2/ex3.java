package SS2;

@FunctionalInterface
public interface ex3 {

    String getPassword();


    default boolean isAuthenticated() {
        String password = getPassword();
        return password != null && !password.isEmpty();
    }
    static String encrypt(String rawPassword) {
        return "ENC_" + rawPassword.hashCode();
    }
}