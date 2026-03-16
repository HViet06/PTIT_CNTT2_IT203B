package SS7.EX1;
public class EmailService {
    public boolean sendConfirmationEmail(Customer customer, Order order) {
        System.out.println("Đã gửi email đến " + customer.getEmail() + ": Đơn hàng " + order.getOrderId() + " đã được tạo");
        return true;
    }
}
