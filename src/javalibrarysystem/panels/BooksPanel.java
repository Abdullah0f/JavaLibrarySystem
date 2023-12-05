package javalibrarysystem.panels;

import javalibrarysystem.models.Book;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksPanel extends JPanel {

    private JTable booksTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, userIdField, genreIdField, authorIdField, publishDateField;
    private JButton addButton, deleteButton, updateButton;

    public BooksPanel() {
        setLayout(new BorderLayout());

        initializeTable();
        initializeForm();

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Book");
        deleteButton = new JButton("Delete Book");
        updateButton = new JButton("Update Book");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        add(new JScrollPane(booksTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(this::addBook);
        deleteButton.addActionListener(this::deleteBook);
        updateButton.addActionListener(this::updateBook);

        // Load initial book data
        loadBookData();
    }

    private void initializeTable() {
        String[] columnNames = {"ID", "Name", "UserID", "GenreID", "AuthorID", "Publish Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        booksTable = new JTable(tableModel);
    }

    private void initializeForm() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        nameField = new JTextField(20);
        userIdField = new JTextField(20);
        genreIdField = new JTextField(20);
        authorIdField = new JTextField(20);
        publishDateField = new JTextField(20); // Format: yyyy-[m]m-[d]d

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("UserID:"));
        formPanel.add(userIdField);
        formPanel.add(new JLabel("GenreID:"));
        formPanel.add(genreIdField);
        formPanel.add(new JLabel("AuthorID:"));
        formPanel.add(authorIdField);
        formPanel.add(new JLabel("Publish Date:"));
        formPanel.add(publishDateField);

        add(formPanel, BorderLayout.NORTH);
    }

    private void addBook(ActionEvent e) {
        // Implement adding a book
        // Convert String to Date: Date.valueOf(publishDateField.getText());
    }

    private void deleteBook(ActionEvent e) {
        // Implement deleting a selected book
    }

    private void updateBook(ActionEvent e) {
        // Implement updating a selected book
    }

    private void loadBookData() {
        tableModel.setRowCount(0); // Clear existing data
        Book.getAll(tableModel);
    }
}
