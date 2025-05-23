package gui;

import javax.swing.*;
import java.awt.*;

public class MainMenuScreen extends JFrame {

    public MainMenuScreen() {
        setTitle("Restaurant Automation - Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton orderBtn = new JButton("Create Order");
        JButton inventoryBtn = new JButton("Manage Inventory");
        JButton reportBtn = new JButton("View Reports");
        JButton exitBtn = new JButton("Exit");

        add(orderBtn);
        add(inventoryBtn);
        add(reportBtn);
        add(exitBtn);

        // Example actions
        orderBtn.addActionListener(e -> System.out.println("Order clicked"));
        exitBtn.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuScreen().setVisible(true));
    }
}