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

    public LoginPanel(MainUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));

        // Create a panel for form elements with FlowLayout
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Initialize components
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Add components to form panel
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        // Create a panel for buttons with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Add formPanel and buttonPanel to main panel
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

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
