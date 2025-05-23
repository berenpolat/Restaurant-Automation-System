package gui;


import backend.OrderManager;
import backend.ReportGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ReportScreen extends JFrame {
  public ReportScreen() {
    setTitle("Daily Report");
    setSize(400, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    displayReport();
  }

  private void displayReport() {
    OrderManager manager = new OrderManager();
    ReportGenerator generator = new ReportGenerator();
    String report = generator.generateDailyReport(manager.getOrdersByDate(new Date()));
    JTextArea reportArea = new JTextArea(report);
    reportArea.setEditable(false);
    add(new JScrollPane(reportArea), BorderLayout.CENTER);
  }
}
