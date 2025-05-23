package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class OrderScreen extends JFrame {
    private JTextField tableField, itemField, qtyField;
    private JTextArea outputArea;
    private List<String> orders = new ArrayList<>();

    private OrderManager orderManager;
    private Inventory inventory;
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
        submit.addActionListener(e -> {
            String order = "Table " + tableField.getText() + ": " +
                qtyField.getText() + " x " + itemField.getText();
            orders.add(order);
            outputArea.setText(String.join("\n", orders));
            tableField.setText("");
            itemField.setText("");
            qtyField.setText("");
        });

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(form, BorderLayout.NORTH);
        add(submit, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        submit.addActionListener(e -> {
            try {
                String item = itemField.getText();
                int qty = Integer.parseInt(qtyField.getText());
                int table = Integer.parseInt(tableField.getText());
                double price = 10.0; // sabit fiyat, istersen menüden çekilebilir

                if (inventory.isAvailable(item, qty)) {
                    OrderItem orderItem = new OrderItem(item, qty, price);
                    Order order = new Order(table, Arrays.asList(orderItem));
                    orderManager.addOrder(order);
                    OrderDAO.saveOrder(order); // DB'ye de kaydet
                    InventoryDAO.deduct(item, qty);




                    outputArea.append(orderItem.toString() + "\n");
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough stock!");
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
