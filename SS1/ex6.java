package SS1;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
class InvalidAgeException2 extends Exception {
    public InvalidAgeException2(String msg) {
        super(msg);
    }
}
class Logger {
    public static void logError(String message) {
        System.out.println("[ERROR] " + LocalDateTime.now() + " - " + message);
    }
}
class User2 {
    private String name;
    private int age;

    public User2(String name) {
        this.name = name;
    }
    public void setAge(int age) throws InvalidAgeException2 {
        if (age < 0) {
            throw new InvalidAgeException2("Tuổi không thể âm!");
        }
        this.age = age;
    }

    public void printName() {
        if (name != null) {
            System.out.println("Tên người dùng: " + name);
        } else {
            System.out.println("Tên người dùng chưa được thiết lập");
        }
    }
}

class FileService {
    public static void saveToFile() throws IOException {
        throw new IOException("Không thể ghi dữ liệu vào file");
    }

}


public class ex6 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {

            System.out.print("Nhập tên: ");
            String name = sc.nextLine();

            User2 user = new User2(name);
            user.printName();

            System.out.print("Nhập năm sinh: ");
            String input = sc.nextLine();

            int year = Integer.parseInt(input);
            int age = 2026 - year;

            user.setAge(age);

            System.out.print("Nhập tổng số người: ");
            int total = sc.nextInt();

            System.out.print("Nhập số nhóm: ");
            int groups = sc.nextInt();

            if (groups == 0) {
                System.out.println("Không thể chia cho 0!");
            } else {
                int result = total / groups;
                System.out.println("Mỗi nhóm có: " + result + " người");
            }

            FileService.saveToFile();

        } catch (NumberFormatException e) {
            Logger.logError("Sai định dạng số: " + e.getMessage());

        } catch (InvalidAgeException2 e) {
            Logger.logError("Lỗi nghiệp vụ: " + e.getMessage());

        } catch (IOException e) {
            Logger.logError("Lỗi hệ thống khi ghi file: " + e.getMessage());

        } finally {
            sc.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên...");
        }

        System.out.println("Chương trình vẫn tiếp tục chạy.");
    }
}
