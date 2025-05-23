package backend;

import backend.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();

    public void createOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrdersByDate(String date) {
        return orders;
    }
}