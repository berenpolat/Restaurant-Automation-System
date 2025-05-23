package gui;

import backend.InventoryDAO;

import javax.swing.*;
import java.awt.*;

public class InventoryEditScreen extends JFrame {
  public InventoryEditScreen() {
    setTitle("Update Inventory");
    setSize(300, 180);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new GridLayout(4, 1));

    JTextField nameField = new JTextField();
    JTextField qtyField = new JTextField();
    JButton updateButton = new JButton("Update/Add");

    add(new JLabel("Ingredient Name:"));
    add(nameField);
    add(new JLabel("Quantity:"));
    add(qtyField);
    add(updateButton);

    updateButton.addActionListener(e -> {
      String name = nameField.getText().trim();
      try {
        int qty = Integer.parseInt(qtyField.getText().trim());
        InventoryDAO.updateOrInsert(name, qty);
        JOptionPane.showMessageDialog(this, "Inventory updated!");
        nameField.setText("");
        qtyField.setText("");
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid quantity!");
      }
    });

    setVisible(true);
  }
}
