package SS7.EX1;
public class OrderCalculator {
    public double calculateTotal(Order order) {
        double total = 0.0;
        for (Order.OrderItem item : order.getItems()) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }
}
