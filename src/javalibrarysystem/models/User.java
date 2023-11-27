package javalibrarysystem.models;

import java.sql.*;

import javalibrarysystem.DB;

public class User {
    private int id;
    private String name;
    private String email;
    private String password; // Consider using hashed passwords for security

    // Constructor, getters and setters
    // ...

    // Method to get all users
    public static ResultSet getAll() {
        DB db = new DB();
        return db.executeQuery("SELECT * FROM Users");
    }

    // Method to insert a new user
    public static void insert(String name, String email, String password) {
        DB db = new DB();
        String query = "INSERT INTO Users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // Consider hashing the password
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a specific user
    public static ResultSet get(String whereClause) {
        DB db = new DB();
        String query = "SELECT * FROM Users WHERE " + whereClause;
        return db.executeQuery(query);
    }

    // Method to delete a user by ID
    public static void delete(int id) {
        DB db = new DB();
        String query = "DELETE FROM Users WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a user's information
    public static void update(int id, String name, String email, String password) {
        DB db = new DB();
        String query = "UPDATE Users SET name = ?, email = ?, password = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // Consider hashing the password
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
