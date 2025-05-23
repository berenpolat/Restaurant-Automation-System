package org.example;

import backend.Order;
import backend.OrderItem;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void testOrderTotalCalculation() {
        Order order = new Order(1, "2025-05-23");
        order.addItem(new OrderItem("Burger", 2, 5.0));
        order.addItem(new OrderItem("Cola", 1, 2.5));

        assertEquals(12.5, order.getTotalAmount(), 0.01);
    }
}