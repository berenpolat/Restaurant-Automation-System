package gui;

import org.example.backend.Order;
import backend.OrderManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

public class OrderScreen extends JFrame {
  private JTextField tableField;
  private JButton submitButton;

  public OrderScreen() {
    setTitle("Create Order");
    setSize(400, 200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    initComponents();
  }

  private void initComponents() {
    JPanel panel = new JPanel(new GridLayout(2, 2));
    tableField = new JTextField();
    submitButton = new JButton("Submit Order");

    submitButton.addActionListener((ActionEvent e) -> {
      Order order = new Order(tableField.getText(), new Date());
      OrderManager manager = new OrderManager();
      manager.createOrder(order);
      JOptionPane.showMessageDialog(this, "Order submitted!");
    });

    panel.add(new JLabel("Table Name:"));
    panel.add(tableField);
    panel.add(submitButton);

    add(panel);
  }
}
