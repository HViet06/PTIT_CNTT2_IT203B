package SS7.EX1;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private double totalAmount;
    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addItem(Product product, int quantity) {
        this.items.add(new OrderItem(product, quantity));
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        StringBuilder itemDetails = new StringBuilder();
        for (OrderItem item : items) {
            itemDetails.append(item.getProduct().getName()).append(" (").append(item.getQuantity()).append(" cái), ");
        }
        if (itemDetails.length() > 0) {
            itemDetails.setLength(itemDetails.length() - 2);
        }
        return "Order ID: " + orderId + ", Customer: " + customer.getName() + ", Items: [" + itemDetails.toString() + "], Total: " + totalAmount;
    }
    public static class OrderItem {
        private Product product;
        private int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
        public Product getProduct() {
            return product;
        }
        public int getQuantity() {
            return quantity;
        }
    }
}
