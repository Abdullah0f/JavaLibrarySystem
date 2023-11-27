package javalibrarysystem;

import javax.swing.*;

import javalibrarysystem.panels.*;

import java.awt.*;

public class MainUI extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public MainUI() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Adding panels to CardLayout
        cardPanel.add(new LoginPanel(this), "LoginPanel");
        cardPanel.add(new RegistrationPanel(this), "RegistrationPanel");
        // Add other panels here, for example:
        // cardPanel.add(new BookPanel(this), "BookPanel");
        // cardPanel.add(new UserPanel(this), "UserPanel");
        // ...

        // Initialize the menu and other UI components
        initUI();

        // Set default card
        cardLayout.show(cardPanel, "LoginPanel");

        // Frame settings
        add(cardPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
    }

    private void initUI() {
        // Setup JFrame properties
        // Create and add JMenuBar with menu items
        // You can use menu items to switch between cards, for example:
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navigate");
        JMenuItem loginItem = new JMenuItem("Login");
        loginItem.addActionListener(e -> switchPanel("LoginPanel"));
        JMenuItem registerItem = new JMenuItem("Register");
        registerItem.addActionListener(e -> switchPanel("RegistrationPanel"));
        // Add menu items for other panels
        menu.add(loginItem);
        menu.add(registerItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    // Method to switch panels
    public void switchPanel(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }
}
