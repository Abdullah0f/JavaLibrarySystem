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
        setLayout(new BorderLayout(10, 10));

        // Create a panel for form elements with FlowLayout
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Initialize components
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");

        // Add components to form panel
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        // Create a panel for the register button with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(registerButton);

        // Add formPanel and buttonPanel to main panel
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

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
