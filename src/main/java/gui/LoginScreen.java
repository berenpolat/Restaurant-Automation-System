package gui;

import backend.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword());

                User loggedInUser = null;

                if (user.equals("admin") && pass.equals("admin")) {
                    loggedInUser = new User("admin", "admin");
                } else if (user.equals("garson") && pass.equals("1234")) {
                    loggedInUser = new User("garson", "garson");
                }

                if (loggedInUser != null) {
                    dispose();
                    new MainMenuScreen(loggedInUser);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                }
            }
        });

        setVisible(true);
    }
}
