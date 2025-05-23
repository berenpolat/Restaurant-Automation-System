package gui;

import backend.Inventory;
import backend.OrderManager;
import backend.User;

import javax.swing.*;
import java.awt.*;

public class MainMenuScreen extends JFrame {
    private OrderManager orderManager = new OrderManager();
    private Inventory inventory = new Inventory();

    private User user;

    public MainMenuScreen(User user) {
        this.user = user;

        setTitle("Main Menu - " + user.getUsername() + " (" + user.getRole() + ")");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1)); // Otomatik satır

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername() + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel);

        JButton orderButton = new JButton("Create Order");
        JButton menuButton = new JButton("View Menu");

        orderButton.addActionListener(e -> new OrderScreen(orderManager, inventory));
        menuButton.addActionListener(e -> new ViewMenu());

        add(orderButton);
        add(menuButton);

        if (user.getRole().equals("admin")) {
            JButton inventoryButton = new JButton("Inventory");
            JButton reportButton = new JButton("Reports");
            JButton historyButton = new JButton("Order History");
            JButton addMenuButton = new JButton("Add Menu Item");
            JButton editInventoryButton = new JButton("Edit Inventory");

            inventoryButton.addActionListener(e -> new InventoryScreen());
            reportButton.addActionListener(e -> new ReportScreen(orderManager));
            historyButton.addActionListener(e -> new OrderHistoryScreen());
            addMenuButton.addActionListener(e -> new MenuAddScreen());
            editInventoryButton.addActionListener(e -> new InventoryEditScreen());

            add(inventoryButton);
            add(reportButton);
            add(historyButton);
            add(addMenuButton);
            add(editInventoryButton);
        }

        setVisible(true);
    }
}
