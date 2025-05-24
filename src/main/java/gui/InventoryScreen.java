package gui;

import backend.InventoryDAO;
import backend.Ingredient;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InventoryScreen extends JFrame {
  public InventoryScreen() {
    setTitle("Inventory");
    setSize(500, 550); // Daha büyük pencere
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout(10, 10));
    String[] columns = {"Ingredient", "Quantity"};
    List<Ingredient> ingredients = InventoryDAO.getAllIngredients();

    DefaultTableModel model = new DefaultTableModel(columns, 0);
    JTable table = new JTable(model);
    table.setRowHeight(28);
    table.setEnabled(false); // düzenlenemez

    boolean lowStockExists = false;
    StringBuilder lowItems = new StringBuilder();

    for (Ingredient ing : ingredients) {
      model.addRow(new Object[]{ing.getName(), ing.getQuantity()});
      if (ing.getQuantity() < 20) {
        lowStockExists = true;
        lowItems.append(ing.getName()).append(" → ").append(ing.getQuantity()).append("\n");
      }
    }

    // Quantity kolonu sağa hizalanır
    DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
    rightAlign.setHorizontalAlignment(SwingConstants.RIGHT);
    table.getColumnModel().getColumn(1).setCellRenderer(rightAlign);

    // Tabloyu direkt ekle (scrollpane yok!)
    add(table.getTableHeader(), BorderLayout.NORTH);
    add(table, BorderLayout.CENTER);

    // Uyarı kutusu
    if (lowStockExists) {
      JTextArea warningArea = new JTextArea("! LOW STOCK !\n" + lowItems);
      warningArea.setEditable(false);
      warningArea.setForeground(Color.RED);
      warningArea.setBackground(new Color(255, 240, 240));
      warningArea.setFont(new Font("Arial", Font.BOLD, 14));
      warningArea.setMargin(new Insets(10, 12, 10, 12));

      JPanel warningPanel = new JPanel(new BorderLayout());
      warningPanel.add(warningArea, BorderLayout.CENTER);
      warningPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

      add(warningPanel, BorderLayout.SOUTH);
    }

    setLocationRelativeTo(null);
    setVisible(true);
  }
}