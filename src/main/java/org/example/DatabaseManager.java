package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
  private static final String URL = "jdbc:mysql://localhost:3306/restaurant";
  private static final String USER = "root";
  private static final String PASS = "12345678";

  public static Connection connect() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASS);
  }

  public static List<MenuItem> fetchMenuItems() {
    List<MenuItem> items = new ArrayList<>();
    String query = "SELECT * FROM menu";

    try (Connection conn = connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        MenuItem item = new MenuItem(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price")
        );
        items.add(item);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return items;
  }
}