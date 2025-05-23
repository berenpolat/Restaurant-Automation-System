package backend;

import java.util.List;

public class Order {
    private int tableNo;
    private List<OrderItem> items;

    public Order(int tableNo, List<OrderItem> items) {
        this.tableNo = tableNo;
        this.items = items;
    }

    public int getTableNo() {
        return tableNo;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }
}
