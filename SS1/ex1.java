package SS1;

import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhập năm sinh của bạn: ");
            String input = sc.nextLine();
            int year = Integer.parseInt(input);
            int age = 2026 - year;
            System.out.println("Tuổi của bạn là: " + age);
        }
        catch (NumberFormatException e) {
            System.out.println("Lỗi: Bạn phải nhập năm sinh bằng số");
        }
        finally {
            sc.close();
        }
    }
}
