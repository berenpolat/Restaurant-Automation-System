package backend;

import java.sql.*;
import java.util.*;

public class InventoryDAO {

  public static List<Ingredient> getAllIngredients() {
    List<Ingredient> list = new ArrayList<>();
    try (Connection conn = DatabaseManager.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM inventory");
      while (rs.next()) {
        list.add(new Ingredient(rs.getString("name"), rs.getInt("quantity")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static boolean isAvailable(String name, int quantity) {
    try (Connection conn = DatabaseManager.getConnection()) {
      PreparedStatement ps = conn.prepareStatement("SELECT quantity FROM inventory WHERE name=?");
      ps.setString(1, name);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getInt("quantity") >= quantity;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void deduct(String name, int quantity) {
    try (Connection conn = DatabaseManager.getConnection()) {
      PreparedStatement ps = conn.prepareStatement("UPDATE inventory SET quantity = quantity - ? WHERE name = ?");
      ps.setInt(1, quantity);
      ps.setString(2, name);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
