package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;

public class MenuAddScreen extends JFrame {
  public MenuAddScreen() {
    setTitle("Add Menu Item");
    setSize(400, 300);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new GridLayout(5, 1));
    JTextField nameField = new JTextField();
    JTextField priceField = new JTextField();
    JTextField ingredientsField = new JTextField();
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
      String priceStr = priceField.getText().trim();
      String ingredientsInput = ingredientsField.getText().trim();

      if (name.isEmpty() || priceStr.isEmpty() || ingredientsInput.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields must be filled.");
        return;
      }

      double price;
      try {
        price = Double.parseDouble(priceStr);
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid price.");
        return;
      }

      // Menüye yemek ekleniyor
      MenuDAO.addMenuItem(name, price);
      System.out.println("Menu item added: " + name + " $" + price);

      // Malzemeleri işle
      String[] parts = ingredientsInput.split(",");
      for (String part : parts) {
        String[] ing = part.trim().split(":");
        if (ing.length != 2) continue;

        try {
          String ingName = ing[0].trim();
          int qty = Integer.parseInt(ing[1].trim());

          System.out.println("Calling DAO: " + name + " - " + ingName + " x" + qty);
          RecipeDAO.addRecipeItem(name, ingName, qty);
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(this, "Invalid ingredient quantity in: " + part);
        }
      }

      JOptionPane.showMessageDialog(this, "Item & ingredients added!");
      nameField.setText("");
      priceField.setText("");
      ingredientsField.setText("");
    });

    setVisible(true);
  }
}