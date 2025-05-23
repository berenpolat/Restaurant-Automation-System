package gui;

import backend.OrderManager;
import backend.ReportGenerator;

import javax.swing.*;
import java.awt.*;

public class ReportScreen extends JFrame {
    public ReportScreen(OrderManager orderManager) {
        setTitle("Daily Report");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        ReportGenerator generator = new ReportGenerator();
        String report = generator.generateReport(orderManager.getOrders());

        JTextArea reportArea = new JTextArea(report);
        reportArea.setEditable(false);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        setVisible(true);
    }
}
