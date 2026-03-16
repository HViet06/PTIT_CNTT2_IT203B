package SS7.EX5;

import java.util.*;

class Product {
    String id;
    String name;
    double price;
    String category;

    public Product(String id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

class Customer {
    String name;
    String email;
    String phone;

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}

class OrderItem {
    Product product;
    int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.price * quantity;
    }
}

class Order {
    String id;
    Customer customer;
    List<OrderItem> items = new ArrayList<>();
    double finalAmount;

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public double getTotal() {
        double sum = 0;
        for (OrderItem i : items) sum += i.getTotal();
        return sum;
    }
}

interface DiscountStrategy {
    double apply(double total);
}

class PercentageDiscount implements DiscountStrategy {
    double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    public double apply(double total) {
        return total - total * percent / 100;
    }
}

class FixedDiscount implements DiscountStrategy {
    double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    public double apply(double total) {
        return total - amount;
    }
}

class HolidayDiscount implements DiscountStrategy {
    public double apply(double total) {
        return total - total * 0.15;
    }
}

interface PaymentMethod {
    void pay(double amount);
}

class CODPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán COD: " + amount);
    }
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán thẻ tín dụng: " + amount);
    }
}

class MomoPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán MoMo: " + amount);
    }
}

class VNPayPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán VNPay: " + amount);
    }
}

interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}

class FileOrderRepository implements OrderRepository {
    List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Đã lưu đơn hàng " + order.id);
    }

    public List<Order> findAll() {
        return orders;
    }
}

class DatabaseOrderRepository implements OrderRepository {
    List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu vào database: " + order.id);
    }

    public List<Order> findAll() {
        return orders;
    }
}

interface NotificationService {
    void send(String message);
}

class EmailNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Đã gửi email xác nhận");
    }
}

class SMSNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Đã gửi SMS");
    }
}

class InvoiceGenerator {
    public void print(Order order, double discount, double finalAmount) {
        System.out.println("=== HÓA ĐƠN ===");
        System.out.println("Khách: " + order.customer.name);
        for (OrderItem i : order.items) {
            System.out.println(i.product.id + " - Số lượng: " + i.quantity +
                    " - Đơn giá: " + i.product.price +
                    " - Thành tiền: " + i.getTotal());
        }
        System.out.println("Tổng tiền: " + order.getTotal());
        System.out.println("Giảm giá: " + discount);
        System.out.println("Cần thanh toán: " + finalAmount);
    }
}

class OrderService {
    OrderRepository repo;
    NotificationService notify;

    public OrderService(OrderRepository repo, NotificationService notify) {
        this.repo = repo;
        this.notify = notify;
    }

    public void createOrder(Order order) {
        repo.save(order);
        notify.send("Đơn hàng đã tạo");
    }
}

public class ex5 {

    static Scanner sc = new Scanner(System.in);
    static List<Product> products = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static OrderRepository repo = new FileOrderRepository();
    static NotificationService notify = new EmailNotification();
    static OrderService service = new OrderService(repo, notify);
    static InvoiceGenerator invoice = new InvoiceGenerator();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Thêm sản phẩm");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Xem đơn hàng");
            System.out.println("5. Tính doanh thu");
            System.out.println("0. Thoát");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) addProduct();
            else if (choice == 2) addCustomer();
            else if (choice == 3) createOrder();
            else if (choice == 4) showOrders();
            else if (choice == 5) revenue();
            else break;
        }
    }

    static void addProduct() {
        System.out.print("Mã: ");
        String id = sc.nextLine();
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Giá: ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.print("Danh mục: ");
        String cat = sc.nextLine();

        products.add(new Product(id, name, price, cat));
        System.out.println("Đã thêm sản phẩm " + id);
    }

    static void addCustomer() {
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("ĐT: ");
        String phone = sc.nextLine();

        customers.add(new Customer(name, email, phone));
        System.out.println("Đã thêm khách hàng");
    }

    static void createOrder() {

        if (customers.isEmpty() || products.isEmpty()) return;

        Customer c = customers.get(0);
        Product p = products.get(0);

        Order order = new Order("ORD00" + (repo.findAll().size() + 1), c);
        order.items.add(new OrderItem(p, 1));

        DiscountStrategy discount = new PercentageDiscount(10);
        PaymentMethod payment = new CreditCardPayment();

        double total = order.getTotal();
        double finalAmount = discount.apply(total);
        double discountAmount = total - finalAmount;

        invoice.print(order, discountAmount, finalAmount);

        payment.pay(finalAmount);

        order.finalAmount = finalAmount;

        service.createOrder(order);
    }

    static void showOrders() {
        for (Order o : repo.findAll()) {
            System.out.println(o.id + " - " + o.customer.name + " - " + o.finalAmount);
        }
    }

    static void revenue() {
        double sum = 0;
        for (Order o : repo.findAll()) sum += o.finalAmount;
        System.out.println("Tổng doanh thu: " + sum);
    }
}
