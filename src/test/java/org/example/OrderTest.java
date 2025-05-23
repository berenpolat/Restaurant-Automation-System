package org.example;

import backend.Order;
import backend.OrderItem;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void testOrderTotalCalculation() {
        Order order = new Order(1, "2025-05-23");
        order.addItem(new OrderItem("Burger", 2, 5.0));
        order.addItem(new OrderItem("Cola", 1, 2.5));

        assertEquals(12.5, order.getTotalAmount(), 0.01);
    }
    @Test
    public void testOrderCreation() {
        Order order = new Order("Table 1", new Date());
        assertEquals("Table 1", order.getTableName());
        assertNotNull(order.getDate());
    }

    @Test
    public void testAddItem() {
        Order order = new Order("Table 2", new Date());
        MenuItem item = new MenuItem("Pizza", 12.0);
        order.addItem(item, 2);
        assertEquals(24.0, order.getTotalPrice());
    }
}