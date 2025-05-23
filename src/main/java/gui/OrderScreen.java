package gui;

import backend.*;

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

    private String order;
    public OrderScreen(OrderManager orderManager, Inventory inventory) {
        this.orderManager = orderManager;
        this.inventory = inventory;
        setTitle("Create Order");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3, 2));
        form.add(new JLabel("Table No:"));
        tableField = new JTextField();
        form.add(tableField);

        form.add(new JLabel("Item:"));
        itemField = new JTextField();
        form.add(itemField);

        form.add(new JLabel("Quantity:"));
        qtyField = new JTextField();
        form.add(qtyField);

        JButton submit = new JButton("Add Order");


        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(form, BorderLayout.NORTH);
        add(submit, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        submit.addActionListener(e -> {
            try {
                String item = itemField.getText().trim();
                int qty = Integer.parseInt(qtyField.getText().trim());
                int table = Integer.parseInt(tableField.getText().trim());
                double price = 10.0;

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
                    OrderItem orderItem = new OrderItem(item, qty, price);
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
                    itemField.setText("");
                    qtyField.setText("");

                } else {
                    JOptionPane.showMessageDialog(this, "Yetersiz stok: Yemekteki malzemeler eksik!");
                }

                tableField.setText("");
                itemField.setText("");
                qtyField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });



        setVisible(true);
    }
}
