package org.example;

public class IntegrationTests {
  public static void main(String[] args) {
    System.out.println("Fetching menu items from DB...");
    for (MenuItem item : DatabaseManager.fetchMenuItems()) {
      System.out.println("Test fetched: " + item.getName() + " - $" + item.getPrice());
    }
  }
}