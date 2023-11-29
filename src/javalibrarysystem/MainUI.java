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
        cardPanel.add(new UsersPanel(this), "UsersPanel");
        cardPanel.add(new BooksPanel(), "BooksPanel");
        cardPanel.add(new AuthorsPanel(), "AuthorsPanel");
        cardPanel.add(new GenresPanel(), "GenresPanel");
        cardPanel.add(new BorrowedBooksPanel(), "BorrowedBooksPanel");

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

        JMenuItem usersItem = new JMenuItem("Users");
        usersItem.addActionListener(e -> switchPanel("UsersPanel"));

        JMenuItem booksItem = new JMenuItem("Books");
        booksItem.addActionListener(e -> switchPanel("BooksPanel"));

        JMenuItem genresItem = new JMenuItem("Genres");
        genresItem.addActionListener(e -> switchPanel("GenresPanel"));

        JMenuItem authorsItem = new JMenuItem("Authors");
        authorsItem.addActionListener(e -> switchPanel("AuthorsPanel"));

        JMenuItem bookAuthorsItem = new JMenuItem("Borrowed Books");
        bookAuthorsItem.addActionListener(e -> switchPanel("BorrowedBooksPanel"));
        // Add menu items for other panels
        menu.add(loginItem);
        menu.add(registerItem);
        menu.add(usersItem);
        menu.add(booksItem);
        menu.add(genresItem);
        menu.add(authorsItem);
        menu.add(bookAuthorsItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    // Method to switch panels
    public void switchPanel(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }
}
