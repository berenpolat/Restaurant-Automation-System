package tests;

import backend.OrderManager;

public class OrderTest {
    public static void main(String[] args) {
        OrderManager manager = new OrderManager();
        if (manager != null) {
            System.out.println("OrderManager test passed!");
        }
    }
}