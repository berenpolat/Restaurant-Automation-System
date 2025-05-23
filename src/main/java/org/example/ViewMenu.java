package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewMenu extends JFrame {
  private JTextArea menuDisplay;

  public ViewMenu() {
    setTitle("Menu View");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initUI();
  }

  private void initUI() {
    menuDisplay = new JTextArea();
    menuDisplay.setEditable(false);
    add(new JScrollPane(menuDisplay), BorderLayout.CENTER);

    JButton refreshButton = new JButton("Refresh Menu");
    refreshButton.addActionListener(e -> refreshMenu());
    add(refreshButton, BorderLayout.SOUTH);
  }

  private void refreshMenu() {
    List<MenuItem> items = DatabaseManager.fetchMenuItems();
    menuDisplay.setText("");
    for (MenuItem item : items) {
      menuDisplay.append("ID: " + item.getId() + ", " + item.getName() + " - $" + item.getPrice() + "\n");
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new ViewMenu().setVisible(true));
  }
}