package tests;

import backend.Inventory;

public class InventoryTest {
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        if (inv.checkAvailability("Cheese", 1)) {
            System.out.println("Inventory checkAvailability test passed!");
        }
    }
}