package javalibrarysystem;

import javax.swing.*;
import javalibrarysystem.panels.*;
import java.awt.*;

public class MainUI extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JMenuBar menuBar;

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

        // Initialize the menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Initialize the menu and other UI components
        initUI();

        // Set default card
        if (LoginManager.isLoggedIn()) {
            cardLayout.show(cardPanel, "BookPanel");
        } else {
            cardLayout.show(cardPanel, "LoginPanel");
        }

        // Frame settings
        add(cardPanel);
        setTitle("Library System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
    }

    private void initUI() {
        updateMenu();
    }


// Method to update the menu
public void updateMenu() {
        menuBar.removeAll();

        JMenu navigateMenu = new JMenu("Navigate");

        if (LoginManager.isLoggedIn()) {
            JMenuItem logoutItem = new JMenuItem("Logout");
            logoutItem.addActionListener(e -> {
                LoginManager.logout();
                switchPanel("LoginPanel");
                updateMenu();  // Update menu after logging out
            });
            navigateMenu.add(logoutItem);

            JMenu usersMenu = new JMenu("Users");
            JMenuItem usersItem = new JMenuItem("Users");
            usersItem.addActionListener(e -> switchPanel("UsersPanel"));
            usersMenu.add(usersItem);

            JMenu booksMenu = new JMenu("Books");
            JMenuItem booksItem = new JMenuItem("Books");
            booksItem.addActionListener(e -> switchPanel("BooksPanel"));
            booksMenu.add(booksItem);

            JMenu authorsMenu = new JMenu("Authors");
            JMenuItem authorsItem = new JMenuItem("Authors");
            authorsItem.addActionListener(e -> switchPanel("AuthorsPanel"));
            authorsMenu.add(authorsItem);

            JMenu genresMenu = new JMenu("Genres");
            JMenuItem genresItem = new JMenuItem("Genres");
            genresItem.addActionListener(e -> switchPanel("GenresPanel"));
            genresMenu.add(genresItem);

            JMenu borrowedBooksMenu = new JMenu("Borrowed Books");
            JMenuItem borrowedBooksItem = new JMenuItem("Borrowed Books");
            borrowedBooksItem.addActionListener(e -> switchPanel("BorrowedBooksPanel"));
            borrowedBooksMenu.add(borrowedBooksItem);

            menuBar.add(navigateMenu);
            menuBar.add(usersMenu);
            menuBar.add(booksMenu);
            menuBar.add(authorsMenu);
            menuBar.add(genresMenu);
            menuBar.add(borrowedBooksMenu);
        } else { // if user is not logged in
            JMenuItem loginItem = new JMenuItem("Login");
            loginItem.addActionListener(e -> {
                switchPanel("LoginPanel");
                updateMenu();  // Update menu after switching panel
            });
            navigateMenu.add(loginItem);

            JMenuItem registerItem = new JMenuItem("Register");
            registerItem.addActionListener(e -> {
                switchPanel("RegistrationPanel");
                updateMenu();  // Update menu after switching panel
            });
            navigateMenu.add(registerItem);

            menuBar.add(navigateMenu);
        }

        menuBar.revalidate();
        menuBar.repaint();
    }

    // Method to switch panels
    public void switchPanel(String cardName) {
        cardLayout.show(cardPanel, cardName);
        updateMenu();  // Update menu after switching panel
    }
}
