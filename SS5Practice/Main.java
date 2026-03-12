package SS5Practice;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductManager manager = new ProductManager();
        while (true) {
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = sc.nextInt();
            try {
                switch(choice) {
                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Tên: ");
                        String name = sc.nextLine();

                        System.out.print("Giá: ");
                        double price = sc.nextDouble();

                        System.out.print("Số lượng: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Danh mục: ");
                        String category = sc.nextLine();
                        Product p = new Product(id, name, price, quantity, category);
                        manager.addProduct(p);

                        System.out.println("Thêm sản phẩm thành công");
                        break;

                    case 2:
                        manager.displayProducts();
                        break;
                    case 3:
                        break;
                    case 4:
                        manager.deleteOutOfStock();
                        break;
                    case 5:
                        System.out.println("Thoát chương trình");
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ");

                }

            } catch (exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
}