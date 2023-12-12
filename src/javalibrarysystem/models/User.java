package javalibrarysystem.models;

import java.sql.*;

import javalibrarysystem.DB;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class User {

    private int id;
    private String name;
    private String email;
    private String password; // Consider using hashed passwords for security

    // Constructor, getters and setters
    // ...
    // Method to get all users
    public static void getAll(DefaultTableModel tableModel) {
    DB db = new DB();
    ResultSet rs = null;
    Statement stmt = null;
    try {
        rs = db.executeQuery("SELECT * FROM library.users");
        stmt = rs.getStatement(); // Get the statement associated with this ResultSet
        while (rs.next()) {
            Object[] row = { rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4) };
            tableModel.addRow(row);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    // Method to insert a new user
        public static boolean insert(String name, String email, String password) {
        DB db = new DB();
        String query = "INSERT INTO library.Users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // Consider hashing the password
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User added successfully!");
                return true;
            } else {
                System.out.println("Failed to add user.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a specific user
    public static ResultSet get(String whereClause) {
        DB db = new DB();
        String query = "SELECT * FROM library.users WHERE " + whereClause;
        return db.executeQuery(query);
    }

    // Method to delete a user by ID
    public static void delete(int id) {
        DB db = new DB();
        String query = "DELETE FROM library.users WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean hasBorrowedBooks(int id) {
    DB db = new DB();
    String query = "SELECT COUNT(*) FROM library.borrowedbooks WHERE userId = ?";
    try (Connection conn = db.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    // Method to update a user's information
    public static void update(int id, String name, String email, String password) {
        DB db = new DB();
        String query = "UPDATE library.users SET name = ?, email = ?, password = ? WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // Consider hashing the password
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean exists(int id) {
        DB db = new DB();
        String query = "SELECT COUNT(*) FROM library.users WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}