package javalibrarysystem.panels;

import javalibrarysystem.models.Author;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorsPanel extends JPanel {

    private JTable authorsTable;
    private DefaultTableModel tableModel;
    private JTextField authorNameField, dobField;
    private JButton addButton, deleteButton, updateButton;

    public AuthorsPanel() {
        setLayout(new BorderLayout());

        initializeTable();
        initializeForm();

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Author");
        deleteButton = new JButton("Delete Author");
        updateButton = new JButton("Update Author");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        add(new JScrollPane(authorsTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(this::addAuthor);
        deleteButton.addActionListener(this::deleteAuthor);
        updateButton.addActionListener(this::updateAuthor);

        // Load initial author data
        loadAuthorData();
    }

    private void initializeTable() {
        String[] columnNames = {"ID", "Author Name", "DOB"};
        tableModel = new DefaultTableModel(columnNames, 0);
        authorsTable = new JTable(tableModel);
    }

    private void initializeForm() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        authorNameField = new JTextField(20);
        dobField = new JTextField(20); // Format: yyyy-[m]m-[d]d

        formPanel.add(new JLabel("Author Name:"));
        formPanel.add(authorNameField);
        formPanel.add(new JLabel("Date of Birth:"));
        formPanel.add(dobField);

        add(formPanel, BorderLayout.NORTH);
    }

    private void addAuthor(ActionEvent e) {
        String authorName = authorNameField.getText();
        String dobText = dobField.getText(); // Convert String to Date

        try {
            Date dob = Date.valueOf(dobText);// Convert String to Date
            Author.insert(authorName, dob);
            loadAuthorData(); // Reload the author data to reflect the new entry
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use the format: 1999-09-09");
        }
    }

    private void deleteAuthor(ActionEvent e) {
        int selectedRow = authorsTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) tableModel.getValueAt(selectedRow, 0);
            if (Author.hasBooks(id)) {
                JOptionPane.showMessageDialog(this, "This author has books and cannot be deleted.");
            } else {
                Author.delete(id);
                loadAuthorData(); // Reload the author data to reflect the deletion
            }
        }
    }

    private void updateAuthor(ActionEvent e) {
        int selectedRow = authorsTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) tableModel.getValueAt(selectedRow, 0);
            String authorName = authorNameField.getText();
            Date dob = Date.valueOf(dobField.getText()); // Convert String to Date
            Author.update(id, authorName, dob);
            loadAuthorData(); // Reload the author data to reflect the update
        }
    }

    private void loadAuthorData() {
        tableModel.setRowCount(0); // Clear existing data
        Author.getAll(tableModel);

    }

}
