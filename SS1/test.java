package SS1;

import java.util.Scanner;

public class test {
    private static Scanner sc = new Scanner(System.in);

    // 1. Nhập số nguyên (int, byte, short, long)
    public static int inputInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }

    public static long inputLong(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên (long) hợp lệ!");
            }
        }
    }

    // 2. Nhập số thực (double, float)
    public static double inputDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số thực (double) hợp lệ!");
            }
        }
    }

    public static float inputFloat(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Float.parseFloat(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số thực (float) hợp lệ!");
            }
        }
    }

    // 3. Nhập chuỗi
    public static String inputString(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    // 4. Nhập boolean (true/false)
    public static boolean inputBoolean(String message) {
        while (true) {
            System.out.print(message + " (true/false): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("true")) return true;
            if (input.equals("false")) return false;
            System.out.println("Vui lòng nhập 'true' hoặc 'false'!");
        }
    }

    // Demo
    public static void main(String[] args) {
        int age = inputInt("Nhập tuổi: ");
        double salary = inputDouble("Nhập lương: ");
        String name = inputString("Nhập tên: ");
        boolean isStudent = inputBoolean("Bạn có phải sinh viên không");

        System.out.println("Kết quả:");
        System.out.println("Tuổi: " + age);
        System.out.println("Lương: " + salary);
        System.out.println("Tên: " + name);
        System.out.println("Sinh viên: " + isStudent);
    }
}
