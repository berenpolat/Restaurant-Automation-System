package tests;

import backend.DatabaseManager;

public class IntegrationTests {
    public static void main(String[] args) {
        DatabaseManager.connect();
        System.out.println("Integration test passed!");
    }
}