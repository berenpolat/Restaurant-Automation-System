package backend;

import java.util.List;

public class Order {
    private int id;
    private List<OrderItem> items;

    public Order(int id, List<OrderItem> items) {
        this.id = id;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}