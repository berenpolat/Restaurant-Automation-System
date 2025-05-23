package org.example.backend;

import java.util.List;

public class ReportGenerator {

  public String generateDailyReport(List<Order> orders) {
    StringBuilder report = new StringBuilder();
    double total = 0;
    report.append("--- Daily Report ---\n");
    for (Order order : orders) {
      report.append(order.toString()).append("\n");
      total += order.getTotalPrice();
    }
    report.append("Total Revenue: $").append(total);
    return report.toString();
  }
}
