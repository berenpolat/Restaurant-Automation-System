package gui;

import backend.*;
import backend.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;


public class OrderScreen extends JFrame {
    private JTextField tableField, itemField, qtyField;
    private JTextArea outputArea;
    private List<String> orders = new ArrayList<>();

    private OrderManager orderManager;
    private Inventory inventory;

    private OrderItem orderItem;

    private String order;
    public OrderScreen(OrderManager orderManager, Inventory inventory) {
        this.orderManager = orderManager;
        this.inventory = inventory;
        setTitle("Create Order");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color background = new Color(245, 245, 245);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel form = new JPanel(new GridLayout(3, 2));
        form.setBackground(background);
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        form.add(new JLabel("Table No:"));
        tableField = new JTextField();
        form.add(tableField);

        form.add(new JLabel("Item:"));
        JComboBox<String> itemBox = new JComboBox<>();
        for (MenuItem menuItem : MenuDAO.fetchMenuItems()) {
            itemBox.addItem(menuItem.getName());
        }
        form.add(itemBox);

        form.add(new JLabel("Quantity:"));
        qtyField = new JTextField();
        form.add(qtyField);

        JButton submit = new JButton("Add Order");
        submit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submit.setBackground(new Color(64, 64, 64));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setPreferredSize(new Dimension(120, 35));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(background);
        buttonPanel.add(submit);
        add(buttonPanel, BorderLayout.CENTER);


        outputArea = new JTextArea();
        outputArea.setEditable(false);


        add(form, BorderLayout.NORTH);
       // add(submit, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        submit.addActionListener(e -> {
            try {
                String item = itemBox.getSelectedItem().toString();
                int qty = Integer.parseInt(qtyField.getText().trim());
                int table = Integer.parseInt(tableField.getText().trim());


                // 1. Malzeme kontrolü
                Map<String, Integer> ingredients = RecipeDAO.getIngredientsForDish(item);
                boolean allAvailable = true;

                for (String ingredient : ingredients.keySet()) {
                    int totalQty = ingredients.get(ingredient) * qty;
                    if (!InventoryDAO.isAvailable(ingredient, totalQty)) {
                        allAvailable = false;
                        break;
                    }
                }

                if (allAvailable) {

                    // 2. Siparişi oluştur

                    String selectedItem = itemBox.getSelectedItem().toString();
                    double price = 0.0;

                    for (MenuItem menuItem : MenuDAO.fetchMenuItems()) {
                        if (menuItem.getName().equals(selectedItem)) {
                            price = menuItem.getPrice();
                            break;
                        }
                    }

                    orderItem = new OrderItem(item, qty, price);


                    Order order = new Order(table, Arrays.asList(orderItem));
                    orderManager.addOrder(order);
                    OrderDAO.saveOrder(order);

                    // 3. Malzemeleri stoktan düş
                    for (String ingredient : ingredients.keySet()) {
                        int totalQty = ingredients.get(ingredient) * qty;
                        InventoryDAO.deduct(ingredient, totalQty);
                    }

                    outputArea.setText(orderItem.toString() + "\n");


                    outputArea.setText(String.join("\n", orders));
                    tableField.setText("");
                    qtyField.setText("");
                    JOptionPane.showMessageDialog(this, "Sipariş başarıyla eklendi!");

                } else {
                    JOptionPane.showMessageDialog(this, "Yetersiz stok: Yemekteki malzemeler eksik!");
                }

                tableField.setText("");
                qtyField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });



        setVisible(true);
    }
}
