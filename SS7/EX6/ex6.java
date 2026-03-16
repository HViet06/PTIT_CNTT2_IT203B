package SS7.EX6;

import java.util.*;

interface DiscountStrategy {
    double apply(double total);
}

interface PaymentMethod {
    void pay(double amount);
}

interface NotificationService {
    void send(String message);
}

interface SalesChannelFactory {
    DiscountStrategy createDiscount();
    PaymentMethod createPayment();
    NotificationService createNotification();
}

class Product {
    String name;
    double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class WebsiteDiscount implements DiscountStrategy {
    public double apply(double total) {
        System.out.println("Áp dụng giảm giá 10% cho đơn hàng website");
        return total * 0.9;
    }
}

class MobileDiscount implements DiscountStrategy {
    public double apply(double total) {
        System.out.println("Áp dụng giảm giá 15% cho lần đầu");
        return total * 0.85;
    }
}

class StoreDiscount implements DiscountStrategy {
    public double apply(double total) {
        System.out.println("Áp dụng giảm giá 5% cho thành viên cửa hàng");
        return total * 0.95;
    }
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng qua cổng thanh toán online");
    }
}

class MomoPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán MoMo tích hợp");
    }
}

class CashPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán tiền mặt tại cửa hàng");
    }
}

class EmailNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Gửi email xác nhận");
    }
}

class PushNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Gửi push notification: " + message);
    }
}

class PrintNotification implements NotificationService {
    public void send(String message) {
        System.out.println("In hóa đơn giấy");
    }
}

class WebsiteFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscount() {
        return new WebsiteDiscount();
    }

    public PaymentMethod createPayment() {
        return new CreditCardPayment();
    }

    public NotificationService createNotification() {
        return new EmailNotification();
    }
}

class MobileFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscount() {
        return new MobileDiscount();
    }

    public PaymentMethod createPayment() {
        return new MomoPayment();
    }

    public NotificationService createNotification() {
        return new PushNotification();
    }
}

class StoreFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscount() {
        return new StoreDiscount();
    }

    public PaymentMethod createPayment() {
        return new CashPayment();
    }

    public NotificationService createNotification() {
        return new PrintNotification();
    }
}

class OrderService {

    SalesChannelFactory factory;

    public OrderService(SalesChannelFactory factory) {
        this.factory = factory;
    }

    public void createOrder(Product product) {

        DiscountStrategy discount = factory.createDiscount();
        PaymentMethod payment = factory.createPayment();
        NotificationService notify = factory.createNotification();

        double total = product.price;
        double finalAmount = discount.apply(total);

        payment.pay(finalAmount);

        notify.send("Đơn hàng thành công");

        System.out.println("Tổng tiền cần thanh toán: " + finalAmount);
    }
}

public class ex6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Website");
        System.out.println("2. Mobile App");
        System.out.println("3. POS");

        int choice = sc.nextInt();

        SalesChannelFactory factory = null;

        if (choice == 1) {
            factory = new WebsiteFactory();
            System.out.println("Bạn đã chọn kênh Website");
        }
        else if (choice == 2) {
            factory = new MobileFactory();
            System.out.println("Bạn đã chọn kênh Mobile App");
        }
        else if (choice == 3) {
            factory = new StoreFactory();
            System.out.println("Bạn đã chọn kênh POS");
        }
        Product product = new Product("Laptop", 15000000);
        OrderService service = new OrderService(factory);
        service.createOrder(product);
    }
}
