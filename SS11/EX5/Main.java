package SS11.EX5;

import SS11.EX5.DoctorService;
import SS11.EX5.Doctor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoctorService service = new DoctorService();

        while (true) {
            System.out.println("\n===== Rikkei-Care =====");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    service.showDoctors();
                    break;

                case 2:
                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine();

                    System.out.print("Nhập tên: ");
                    String name = sc.nextLine();

                    System.out.print("Nhập chuyên khoa: ");
                    String sp = sc.nextLine();

                    service.addDoctor(new Doctor(id, name, sp));
                    break;

                case 3:
                    service.statistic();
                    break;

                case 4:
                    System.exit(0);

                default:
                    System.out.println("Chọn sai!");
            }
        }
    }
}