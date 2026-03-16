package SS7;

import java.util.*;

class Order {
    private String id;
    public Order(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}

interface NotificationService {
    void send(String message, String recipient);
}

class FileOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu đơn hàng vào file: " + order.getId());
    }

    public List<Order> findAll() {
        return orders;
    }
}

class DatabaseOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu đơn hàng vào database: " + order.getId());
    }

    public List<Order> findAll() {
        return orders;
    }
}

class EmailService implements NotificationService {

    public void send(String message, String recipient) {
        System.out.println("Gửi email: " + message);
    }
}

class SMSNotification implements NotificationService {

    public void send(String message, String recipient) {
        System.out.println("Gửi SMS: " + message);
    }
}

class OrderService {

    private OrderRepository orderRepository;
    private NotificationService notificationService;

    public OrderService(OrderRepository orderRepository, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    public void createOrder(String id) {
        Order order = new Order(id);
        orderRepository.save(order);
        notificationService.send("Đơn hàng " + id + " đã được tạo", "customer");
    }
}

public class ex4 {
    public static void main(String[] args) {
        OrderRepository repo1 = new FileOrderRepository();
        NotificationService notify1 = new EmailService();

        OrderService service1 = new OrderService(repo1, notify1);
        service1.createOrder("ORD001");

        System.out.println();

        OrderRepository repo2 = new DatabaseOrderRepository();
        NotificationService notify2 = new SMSNotification();

        OrderService service2 = new OrderService(repo2, notify2);
        service2.createOrder("ORD002");
    }
}
