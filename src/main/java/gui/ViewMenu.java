package gui;

import backend.Inventory;
import backend.MenuDAO;
import backend.MenuItem;
import backend.OrderManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewMenu extends JFrame {
    public ViewMenu() {
        setTitle("Restaurant Menu");
        setSize(350, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

       /* // Veritabanından menü çek
        List<MenuItem> menuList = MenuDAO.fetchMenuItems();

        // Veriyi tabloya uygun hale getir
        Object[][] data = new Object[menuList.size()][2];
        for (int i = 0; i < menuList.size(); i++) {
            data[i][0] = menuList.get(i).getName();
            data[i][1] = "$" + menuList.get(i).getPrice();
        }

        String[] columns = {"Dish", "Price"};
        JTable menuTable = new JTable(data, columns);
        add(new JScrollPane(menuTable), BorderLayout.CENTER);

        setVisible(true);
    }
}*/
        Color bg = new Color(245, 245, 245);
        JPanel cardPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        cardPanel.setBackground(bg);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<MenuItem> menuItems = MenuDAO.fetchMenuItems();

        for (MenuItem item : menuItems) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            JLabel nameLabel = new JLabel(item.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel priceLabel = new JLabel("$" + item.getPrice(), SwingConstants.CENTER);
            priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton addButton = new JButton("Add to Order");
            addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            addButton.setBackground(new Color(60, 130, 200));
            addButton.setForeground(Color.WHITE);
            addButton.setFocusPainted(false);

            addButton.addActionListener(e -> {
                OrderManager manager = new OrderManager();
                Inventory inventory = new Inventory();
                OrderScreen orderScreen = new OrderScreen(manager, inventory);
                orderScreen.setVisible(true);
            });

            card.add(nameLabel);
            card.add(Box.createVerticalStrut(5));
            card.add(priceLabel);
            card.add(Box.createVerticalStrut(10));
            card.add(addButton);

            cardPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}