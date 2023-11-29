package javalibrarysystem.panels;

import javalibrarysystem.MainUI;
import javalibrarysystem.models.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersPanel extends JPanel {
    private JTable usersTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, emailField, passwordField;
    private JButton addButton, deleteButton, updateButton;
    private MainUI mainFrame; // Reference to the MainUI frame

    public UsersPanel(MainUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        initializeTable();
        initializeForm();

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add User");
        deleteButton = new JButton("Delete User");
        updateButton = new JButton("Update User");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        add(new JScrollPane(usersTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(this::addUser);
        deleteButton.addActionListener(this::deleteUser);
        updateButton.addActionListener(this::updateUser);

        // Load initial user data
        loadUserData();
    }

    private void initializeTable() {
        String[] columnNames = { "ID", "Name", "Email" };
        tableModel = new DefaultTableModel(columnNames, 0);
        usersTable = new JTable(tableModel);
    }

    private void initializeForm() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JTextField(20);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        add(formPanel, BorderLayout.NORTH);
    }

    private void addUser(ActionEvent e) {
        // Implement adding a user
        // Example: User.insert(nameField.getText(), emailField.getText(),
        // passwordField.getText());
    }

    private void deleteUser(ActionEvent e) {
        // Implement deleting a selected user
        // Example: int selectedRow = usersTable.getSelectedRow();
        // if(selectedRow >= 0) { User.delete((Integer)
        // tableModel.getValueAt(selectedRow, 0)); }
    }

    private void updateUser(ActionEvent e) {
        // Implement updating a selected user
    }

    private void loadUserData() {
        return;
        // try {
        // ResultSet rs = User.getAll();
        // while (rs.next()) {
        // Object[] row = { rs.getInt("id"), rs.getString("name"), rs.getString("email")
        // };
        // tableModel.addRow(row);
        // }
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
    }
}
