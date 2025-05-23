package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InventoryScreen extends JFrame {
    public InventoryScreen() {
        setTitle("Inventory");
        setSize(350, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        List<Ingredient> ingredients = InventoryDAO.getAllIngredients();
        Object[][] data = new Object[ingredients.size()][2];

        for (int i = 0; i < ingredients.size(); i++) {
            data[i][0] = ingredients.get(i).getName();
            data[i][1] = ingredients.get(i).getQuantity();
        }

        String[] columns = {"Ingredient", "Quantity"};
        JTable table = new JTable(data, columns);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }
}
