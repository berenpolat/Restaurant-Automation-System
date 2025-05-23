package gui;

import backend.MenuDAO;
import backend.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewMenu extends JFrame {
    public ViewMenu() {
        setTitle("Restaurant Menu");
        setSize(350, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Veritabanından menü çek
        List<MenuItem> menuList = MenuDAO.fetchMenuItems();

        // Veriyi tabloya uygun hale getir
        Object[][] data = new Object[menuList.size()][2];
        for (int i = 0; i < menuList.size(); i++) {
            data[i][0] = menuList.get(i).getName();
            data[i][1] = "$" + menuList.get(i).getPrice();
        }

        String[] columns = {"Dish", "Price"};
        JTable menuTable = new JTable(data, columns);
        add(new JScrollPane(menuTable), BorderLayout.CENTER);

        setVisible(true);
    }
}
