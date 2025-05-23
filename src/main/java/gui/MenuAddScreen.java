package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MenuAddScreen extends JFrame {
  public MenuAddScreen() {
    setTitle("Add Menu Item");
    setSize(350, 250);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new GridLayout(5, 1));

    JTextField nameField = new JTextField();
    JTextField priceField = new JTextField();
    JTextField ingredientsField = new JTextField(); // yeni alan
    JButton addButton = new JButton("Add");

    add(new JLabel("Dish Name:"));
    add(nameField);
    add(new JLabel("Price:"));
    add(priceField);
    add(new JLabel("Ingredients (name:qty, ...):"));
    add(ingredientsField);
    add(addButton);

    addButton.addActionListener(e -> {
      String name = nameField.getText().trim();
      String ingredientsInput = ingredientsField.getText().trim();

      try {
        double price = Double.parseDouble(priceField.getText().trim());
        MenuDAO.addMenuItem(name, price);

        // Ingredient parsing
        String[] parts = ingredientsInput.split(",");
        for (String part : parts) {
          String[] ing = part.trim().split(":");
          if (ing.length == 2) {
            String ingName = ing[0].trim();
            int qty = Integer.parseInt(ing[1].trim());
            RecipeDAO.addRecipeItem(name, ingName, qty);
          }
        }

        JOptionPane.showMessageDialog(this, "Item & ingredients added!");
        nameField.setText("");
        priceField.setText("");
        ingredientsField.setText("");
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
      }
    });

    setVisible(true);
  }
}
