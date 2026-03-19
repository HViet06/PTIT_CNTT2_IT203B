package SS8.EX6;

import java.util.*;

interface DiscountStrategy {
    double apply(double amount);
}

interface PaymentMethod {
    void pay(double amount);
}

interface NotificationService {
    void notifyUser();
}

interface SalesChannelFactory {
    DiscountStrategy createDiscountStrategy();
    PaymentMethod createPaymentMethod();
    NotificationService createNotificationService();
}

class WebsiteDiscount implements DiscountStrategy {
    public double apply(double amount) {
        double discount = amount * 0.1;
        System.out.println("Áp dụng giảm giá 10%: " + discount);
        return amount - discount;
    }
}

class FirstTimeDiscount implements DiscountStrategy {
    public double apply(double amount) {
        double discount = amount * 0.15;
        System.out.println("Áp dụng giảm giá 15%: " + discount);
        return amount - discount;
    }
}

class MemberDiscount implements DiscountStrategy {
    public double apply(double amount) {
        double discount = amount * 0.05;
        System.out.println("Áp dụng giảm giá 5%: " + discount);
        return amount - discount;
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

class CODPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán khi nhận hàng: " + amount);
    }
}

class EmailNotification implements NotificationService {
    public void notifyUser() {
        System.out.println("Gửi email: Đơn hàng thành công");
    }
}

class PushNotification implements NotificationService {
    public void notifyUser() {
        System.out.println("Gửi push notification: Đơn hàng thành công");
    }
}

class PrintReceipt implements NotificationService {
    public void notifyUser() {
        System.out.println("In hóa đơn: Đơn hàng thành công");
    }
}

class WebsiteFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() {
        return new WebsiteDiscount();
    }

    public PaymentMethod createPaymentMethod() {
        return new CreditCardPayment();
    }

    public NotificationService createNotificationService() {
        return new EmailNotification();
    }
}

class MobileAppFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() {
        return new FirstTimeDiscount();
    }

    public PaymentMethod createPaymentMethod() {
        return new MomoPayment();
    }

    public NotificationService createNotificationService() {
        return new PushNotification();
    }
}

class POSFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() {
        return new MemberDiscount();
    }

    public PaymentMethod createPaymentMethod() {
        return new CODPayment();
    }

    public NotificationService createNotificationService() {
        return new PrintReceipt();
    }
}

class OrderService {
    private DiscountStrategy discount;
    private PaymentMethod payment;
    private NotificationService notification;

    public OrderService(SalesChannelFactory factory) {
        this.discount = factory.createDiscountStrategy();
        this.payment = factory.createPaymentMethod();
        this.notification = factory.createNotificationService();
    }

    public void processOrder(double price, int quantity) {
        double total = price * quantity;
        double finalAmount = discount.apply(total);
        payment.pay(finalAmount);
        notification.notifyUser();
    }
}

public class ex6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Website");
            System.out.println("2. Mobile App");
            System.out.println("3. POS");
            System.out.println("0. Thoát");
            System.out.print("Chọn kênh: ");

            int choice = sc.nextInt();
            if (choice == 0) break;

            SalesChannelFactory factory = null;

            if (choice == 1) {
                factory = new WebsiteFactory();
                System.out.println("Bạn đã chọn Website");
            } else if (choice == 2) {
                factory = new MobileAppFactory();
                System.out.println("Bạn đã chọn Mobile App");
            } else if (choice == 3) {
                factory = new POSFactory();
                System.out.println("Bạn đã chọn POS");
            }

            System.out.print("Nhập giá sản phẩm: ");
            double price = sc.nextDouble();
            System.out.print("Nhập số lượng: ");
            int qty = sc.nextInt();

            OrderService order = new OrderService(factory);
            order.processOrder(price, qty);
        }
    }
}