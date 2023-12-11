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
        setTitle("Java Library System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
    }

    private void initUI() {
        // Setup JFrame properties
        // Create and add JMenuBar with menu items
        // You can use menu items to switch between cards, for example:
        JMenuBar menuBar = new JMenuBar();

        JMenu navigateMenu = new JMenu("Navigate");
        JMenuItem loginItem = new JMenuItem("Login");
        loginItem.addActionListener(e -> switchPanel("LoginPanel"));
        JMenuItem registerItem = new JMenuItem("Register");
        registerItem.addActionListener(e -> switchPanel("RegistrationPanel"));
        navigateMenu.add(loginItem);
        navigateMenu.add(registerItem);

        JMenu usersMenu = new JMenu("Users");
        JMenuItem usersItem = new JMenuItem("Users");
        usersItem.addActionListener(e -> switchPanel("UsersPanel"));
        usersMenu.add(usersItem);

        JMenu booksMenu = new JMenu("Books");
        JMenuItem booksItem = new JMenuItem("Books");
        booksItem.addActionListener(e -> switchPanel("BooksPanel"));
        booksMenu.add(booksItem);

        JMenu genresMenu = new JMenu("Genres");
        JMenuItem genresItem = new JMenuItem("Genres");
        genresItem.addActionListener(e -> switchPanel("GenresPanel"));
        genresMenu.add(genresItem);

        JMenu authorsMenu = new JMenu("Authors");
        JMenuItem authorsItem = new JMenuItem("Authors");
        authorsItem.addActionListener(e -> switchPanel("AuthorsPanel"));
        authorsMenu.add(authorsItem);

        JMenu borrowedBooksMenu = new JMenu("Borrowed Books");
        JMenuItem bookAuthorsItem = new JMenuItem("Borrowed Books");
        bookAuthorsItem.addActionListener(e -> switchPanel("BorrowedBooksPanel"));
        borrowedBooksMenu.add(bookAuthorsItem);

        menuBar.add(navigateMenu);
        menuBar.add(usersMenu);
        menuBar.add(booksMenu);
        menuBar.add(genresMenu);
        menuBar.add(authorsMenu);
        menuBar.add(borrowedBooksMenu);

        setJMenuBar(menuBar);
    }

    // Method to switch panels
    public void switchPanel(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }
}
