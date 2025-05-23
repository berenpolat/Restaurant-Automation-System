package gui;

import backend.Inventory;
import backend.OrderManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuScreen extends JFrame {
    private OrderManager orderManager = new OrderManager();
    private Inventory inventory = new Inventory();
    public MainMenuScreen() {
        setTitle("Main Menu");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        JButton orderButton = new JButton("Create Order");
        JButton inventoryButton = new JButton("Inventory");
        JButton menuButton = new JButton("View Menu");
        JButton reportButton = new JButton("Reports");

        orderButton.addActionListener(e -> new OrderScreen(orderManager, inventory));
        inventoryButton.addActionListener(e -> new InventoryScreen());
        menuButton.addActionListener(e -> new ViewMenu());
        reportButton.addActionListener(e -> new ReportScreen(orderManager));

        add(orderButton);
        add(inventoryButton);
        add(menuButton);
        add(reportButton);

        setVisible(true);
    }
}
