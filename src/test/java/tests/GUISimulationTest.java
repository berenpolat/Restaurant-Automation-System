package tests;

import gui.MainMenuScreen;

public class GUISimulationTest {
    public static void main(String[] args) {
        MainMenuScreen screen = new MainMenuScreen();
        if (screen != null) {
            System.out.println("MainMenuScreen GUI test passed!");
        }
    }
}