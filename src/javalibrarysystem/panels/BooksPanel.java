package javalibrarysystem.panels;

import javalibrarysystem.models.*;
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
    private JTextField nameField, genreIdField, authorIdField, publishDateField;
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
        addButton.addActionListener(e -> this.addBook(e));
        deleteButton.addActionListener(e -> this.deleteBook(e));
        updateButton.addActionListener(e -> this.updateBook(e));

        // Load initial book data
        loadBookData();
    }

    private void initializeTable() {
        String[] columnNames = {"ID", "Name", "GenreID", "AuthorID", "Publish Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        booksTable = new JTable(tableModel);
    }

    private void initializeForm() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        nameField = new JTextField(20);
        genreIdField = new JTextField(20);
        authorIdField = new JTextField(20);
        publishDateField = new JTextField(20); // Format: yyyy-[m]m-[d]d

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("GenreID:"));
        formPanel.add(genreIdField);
        formPanel.add(new JLabel("AuthorID:"));
        formPanel.add(authorIdField);
        formPanel.add(new JLabel("Publish Date:"));
        formPanel.add(publishDateField);

        add(formPanel, BorderLayout.NORTH);
    }

    private void addBook(ActionEvent e) {
        String name = nameField.getText();
        int genreId = Integer.parseInt(genreIdField.getText());
        int authorId = Integer.parseInt(authorIdField.getText());
        String publishDateText = publishDateField.getText();
        if (!Genre.exists(genreId)) {
            JOptionPane.showMessageDialog(this, "Genre ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Author.exists(authorId)) {
            JOptionPane.showMessageDialog(this, "Author ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Date publishDate = Date.valueOf(publishDateText);// Convert String to Date
            Book.insert(name, genreId, authorId, publishDate);
            loadBookData();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use the format: 1999-09-09");
        }
    }

private void deleteBook(ActionEvent e) {
    int selectedRow = booksTable.getSelectedRow();
    if (selectedRow >= 0) {
        int bookId = (int) booksTable.getValueAt(selectedRow, 0);
        if (Book.isBorrowed(bookId)) {
            JOptionPane.showMessageDialog(this, "This book is currently borrowed and cannot be deleted.");
        } else {
            Book.delete(bookId);
            loadBookData(); // Reload the book data to reflect the deletion
        }
    }
}

    private void updateBook(ActionEvent e) {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow >= 0) {
            int bookId = (int) booksTable.getValueAt(selectedRow, 0);
            String name = nameField.getText();
            int genreId = Integer.parseInt(genreIdField.getText());
            int authorId = Integer.parseInt(authorIdField.getText());
            Date publishDate = Date.valueOf(publishDateField.getText());

            Book.update(bookId, name, genreId, authorId, publishDate);

            loadBookData();
        }
    }

    private void loadBookData() {
        tableModel.setRowCount(0); // Clear existing data
        Book.getAll(tableModel);
    }
}
