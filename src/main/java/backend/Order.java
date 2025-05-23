package backend;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<OrderItem> items;
    private String date;

    public Order(int orderId, String date) {
        this.orderId = orderId;
        this.date = date;
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }

    public int getOrderId() {
        return orderId;
    }

    public String getDate() {
        return date;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}