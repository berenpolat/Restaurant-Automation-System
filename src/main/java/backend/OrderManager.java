package backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderManager {
  private List<Order> orders;

  public OrderManager() {
    this.orders = new ArrayList<>();
  }

  public void createOrder(Order order) {
    orders.add(order);
    Inventory.getInstance().deductIngredients(order);
  }

  public List<Order> getOrdersByDate(Date date) {
    List<Order> result = new ArrayList<>();
    for (Order o : orders) {
      if (o.getDate().equals(date)) {
        result.add(o);
      }
    }
    return result;
  }

  public List<Order> getAllOrders() {
    return orders;
  }
}
