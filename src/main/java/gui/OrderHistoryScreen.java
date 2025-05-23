package gui;
import backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderHistoryScreen extends JFrame {
  public OrderHistoryScreen() {
    setTitle("Order History");
    setSize(600, 400);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());

    JTextArea area = new JTextArea();
    area.setEditable(false);

    List<String> orders =OrderHistoryDAO.fetchAllOrders();
    for (String line : orders) {
      area.append(line + "\n");
    }

    add(new JScrollPane(area), BorderLayout.CENTER);
    setVisible(true);
  }
}
