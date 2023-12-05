package javalibrarysystem.panels;

import javalibrarysystem.models.Genre;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenresPanel extends JPanel {

    private JTable genresTable;
    private DefaultTableModel tableModel;
    private JTextField genreField;
    private JButton addButton, deleteButton, updateButton;

    public GenresPanel() {
        setLayout(new BorderLayout());

        initializeTable();
        initializeForm();

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Genre");
        deleteButton = new JButton("Delete Genre");
        updateButton = new JButton("Update Genre");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        add(new JScrollPane(genresTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(this::addGenre);
        deleteButton.addActionListener(this::deleteGenre);
        updateButton.addActionListener(this::updateGenre);

        // Load initial genre data
        loadGenreData();
    }

    private void initializeTable() {
        String[] columnNames = {"ID", "Genre"};
        tableModel = new DefaultTableModel(columnNames, 0);
        genresTable = new JTable(tableModel);
    }

    private void initializeForm() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        genreField = new JTextField(20);

        formPanel.add(new JLabel("Genre:"));
        formPanel.add(genreField);

        add(formPanel, BorderLayout.NORTH);
    }

    private void addGenre(ActionEvent e) {
        String genre = genreField.getText();
        Genre.insert(genre);
        loadGenreData(); // Reload the genre data to reflect the new entry
    }

    private void deleteGenre(ActionEvent e) {
        int selectedRow = genresTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) tableModel.getValueAt(selectedRow, 0);
            Genre.delete(id);
            loadGenreData(); // Reload the genre data to reflect the deletion
        }
    }

    private void updateGenre(ActionEvent e) {
        // Implement updating a selected genre
    }

    private void loadGenreData() {
        tableModel.setRowCount(0); // Clear existing data
        Genre.getAll(tableModel);
    }
}
