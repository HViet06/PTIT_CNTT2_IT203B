package SS7.EX1;
public class OrderRepository {
    public boolean save(Order order) {
        System.out.println("Đã lưu đơn hàng " + order.getOrderId());
        return true;
    }

    public Order findById(String orderId) {
        System.out.println("Tìm kiếm đơn hàng " + orderId);
        return null;
    }
}
