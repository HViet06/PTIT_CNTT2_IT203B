package SS1;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String msg) {
        super(msg);
    }
}
class user {
    private int age;
    public void setAge(int age) throws InvalidAgeException2 {
        if (age < 0) {
            throw new InvalidAgeException2("Tuổi không thể âm!");
        }
        this.age = age;
    }
    public int getAge() {
        return age;
    }
}
public class ex5 {
    public static void main(String[] args) {
        user u = new user();
        try {
            u.setAge(-3);
            System.out.println("Tuổi: " + u.getAge());
        } catch (InvalidAgeException2 e) {
            System.out.println("Lỗi nghiệp vụ: " + e.getMessage());
        }
    }
}