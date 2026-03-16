package SS7.EX1;
public class Main {
    public static void main(String[] args) {
        System.out.println("Tạo sản phẩm: SP01 - Laptop - 15000000, SP02 - Chuột - 300000");
        Product product1 = new Product("SP01", "Laptop", 15000000);
        Product product2 = new Product("SP02", "Chuột", 300000);
        System.out.println("Đã thêm sản phẩm " + product1.getProductId() + ", " + product2.getProductId());

        System.out.println("Tạo khách hàng: Nguyễn Văn A - a@example.com");
        Customer customer = new Customer("KH001", "Nguyễn Văn A", "a@example.com");
        System.out.println("Đã thêm khách hàng");

        System.out.println("Tạo đơn hàng: SP01 (1 cái), SP02 (2 cái)");
        String orderId = "ORD001";
        Order order = new Order(orderId, customer);
        order.addItem(product1, 1);
        order.addItem(product2, 2);
        System.out.println("Đơn hàng " + order.getOrderId() + " được tạo");

        System.out.println("Tính tổng tiền");
        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.calculateTotal(order);
        order.setTotalAmount(total);
        System.out.println("Tổng tiền: " + (int) order.getTotalAmount());

        System.out.println("Lưu đơn hàng");
        OrderRepository repository = new OrderRepository();
        repository.save(order);

        System.out.println("Gửi email xác nhận");
        EmailService emailService = new EmailService();
        emailService.sendConfirmationEmail(customer, order);
    }
}
