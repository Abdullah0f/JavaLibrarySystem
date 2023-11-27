package javalibrarysystem.panels;

import javax.swing.*;

import javalibrarysystem.LoginManager;
import javalibrarysystem.MainUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationPanel extends JPanel {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private MainUI mainUI;

    public RegistrationPanel(MainUI mainFrame) {
        this.mainUI = mainFrame;
        setLayout(new GridLayout(4, 2, 10, 10));

        // Initialize components
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");

        // Add components to panel
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(registerButton);

        // Event handling
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegistration();
            }
        });
    }

    private void handleRegistration() {
        String name = nameField.getText();
        String email = emailField.getText();
        char[] password = passwordField.getPassword();

        if (LoginManager.register(name, email, new String(password))) {
            // Registration successful
            JOptionPane.showMessageDialog(this, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Switch back to login panel
            mainUI.switchPanel("LoginPanel");
        } else {
            // Registration failed
            JOptionPane.showMessageDialog(this, "Registration Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
