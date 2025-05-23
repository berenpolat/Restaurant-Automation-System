package org.example;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class OrderTest {

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