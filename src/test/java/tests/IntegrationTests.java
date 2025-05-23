package tests;

import backend.DatabaseManager;

public class IntegrationTests {
    public static void main(String[] args) {
        DatabaseManager.getConnection();
        System.out.println("Integration test passed!");
    }
}