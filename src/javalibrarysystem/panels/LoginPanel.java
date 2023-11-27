package javalibrarysystem.panels;

import javax.swing.*;

import javalibrarysystem.LoginManager;
import javalibrarysystem.MainUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private MainUI mainFrame; // Reference to the MainUI frame

    // Constructor now receives MainUI reference
    public LoginPanel(MainUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(3, 2, 10, 10));

        // Initialize components
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Add components to panel
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        // Event handling
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegistration();
            }
        });
    }

    private void handleLogin() {
        String email = emailField.getText();
        char[] password = passwordField.getPassword();

        if (LoginManager.login(email, new String(password))) {
            // Login successful
            JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Switch to another panel or functionality as needed
            // Example: mainFrame.switchPanel("SomeOtherPanelName");
        } else {
            // Login failed
            JOptionPane.showMessageDialog(this, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegistration() {
        // Switch to the registration panel
        mainFrame.switchPanel("RegistrationPanel");
    }
}
