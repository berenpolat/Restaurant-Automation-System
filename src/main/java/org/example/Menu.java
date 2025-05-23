package org.example;

import java.util.ArrayList;
import java.util.List;

public class Menu {
  private List<MenuItem> items;

  public Menu() {
    items = new ArrayList<>();
  }

  public void addItem(MenuItem item) {
    items.add(item);
  }

  public void removeItem(int id) {
    items.removeIf(item -> item.getId() == id);
  }

  public List<MenuItem> getItems() {
    return items;
  }

  public MenuItem getItemById(int id) {
    for (MenuItem item : items) {
      if (item.getId() == id) return item;
    }
    return null;
  }
}
