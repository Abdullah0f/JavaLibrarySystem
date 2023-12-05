package javalibrarysystem.panels;

import javalibrarysystem.models.BorrowedBook;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowedBooksPanel extends JPanel {

    private JTable borrowedBooksTable;
    private DefaultTableModel tableModel;
    private JTextField bookIdField, userIdField, borrowDateField;
    private JButton addButton, deleteButton, updateButton;

    public BorrowedBooksPanel() {
        setLayout(new BorderLayout());

        initializeTable();
        initializeForm();

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Borrowed Book");
        deleteButton = new JButton("Delete Borrowed Book");
        updateButton = new JButton("Update Borrowed Book");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        add(new JScrollPane(borrowedBooksTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(this::addBorrowedBook);
        deleteButton.addActionListener(this::deleteBorrowedBook);
        updateButton.addActionListener(this::updateBorrowedBook);

        // Load initial borrowed book data
        loadBorrowedBookData();
    }

    private void initializeTable() {
        String[] columnNames = {"ID", "Book ID", "User ID", "Borrow Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        borrowedBooksTable = new JTable(tableModel);
    }

    private void initializeForm() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        bookIdField = new JTextField(20);
        userIdField = new JTextField(20);
        borrowDateField = new JTextField(20); // Format: yyyy-[m]m-[d]d

        formPanel.add(new JLabel("Book ID:"));
        formPanel.add(bookIdField);
        formPanel.add(new JLabel("User ID:"));
        formPanel.add(userIdField);
        formPanel.add(new JLabel("Borrow Date:"));
        formPanel.add(borrowDateField);

        add(formPanel, BorderLayout.NORTH);
    }

    private void addBorrowedBook(ActionEvent e) {
        int bookId = Integer.parseInt(bookIdField.getText());
        int userId = Integer.parseInt(userIdField.getText());
        Date borrowDate = Date.valueOf(borrowDateField.getText()); // Convert String to Date
        BorrowedBook.insert(bookId, userId, borrowDate);
        loadBorrowedBookData(); // Reload the data to reflect the new entry
    }

    private void deleteBorrowedBook(ActionEvent e) {
        int selectedRow = borrowedBooksTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) tableModel.getValueAt(selectedRow, 0);
            BorrowedBook.delete(id);
            loadBorrowedBookData(); // Reload the data to reflect the deletion
        }
    }

    private void updateBorrowedBook(ActionEvent e) {
        // Implement updating a selected borrowed book record
    }

    private void loadBorrowedBookData() {
        tableModel.setRowCount(0); // Clear existing data
        BorrowedBook.getAll(tableModel);
    }
}
