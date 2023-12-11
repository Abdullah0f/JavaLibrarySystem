package javalibrarysystem.models;

import java.sql.*;

import javalibrarysystem.DB;
import javax.swing.table.DefaultTableModel;

public class Author {
    private int id;
    private String authorName;
    private Date dob; // java.sql.Date

    public Author() {
        // Default constructor
    }

    // Constructor
    public Author(int id, String authorName, Date dob) {
        this.id = id;
        this.authorName = authorName;
        this.dob = dob;
    }

    // Getters and setters
    // ...

    // Method to get all authors
    public static void getAll(DefaultTableModel tableModel) {
    DB db = new DB();
    ResultSet rs = null;
    Statement stmt = null;
    try {
        rs = db.executeQuery("SELECT * FROM library.authors");
        stmt = rs.getStatement(); // Get the statement associated with this ResultSet
        while (rs.next()) {
            Object[] row = { rs.getInt(1),rs.getString(2), rs.getDate(3) };
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

    // Method to insert a new author
    public static void insert(String authorName, Date dob) {
        DB db = new DB();
        String query = "INSERT INTO library.authors (authorName, DOB) VALUES (?, ?)";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, authorName);
            pstmt.setDate(2, dob);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a specific author (based on a where clause)
    public static ResultSet get(String whereClause) {
        DB db = new DB();
        String query = "SELECT * FROM library.authors WHERE " + whereClause;
        return db.executeQuery(query);
    }

    // Method to delete an author by ID
    public static void delete(int id) {
        DB db = new DB();
        String query = "DELETE FROM library.authors WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an author's information
    public static void update(int id, String authorName, Date dob) {
        DB db = new DB();
        String query = "UPDATE library.authors SET authorName = ?, DOB = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, authorName);
            pstmt.setDate(2, dob);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean exists(int id) {
        DB db = new DB();
        String query = "SELECT COUNT(*) FROM library.authors WHERE id = ?";
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

// usage
// // Insert a new author
// Author.insert("J.K. Rowling", Date.valueOf("1965-07-31"));

// // Get all authors
// ResultSet allAuthors = Author.getAll();

// // Get a specific author
// ResultSet specificAuthor = Author.get("id = 1");

// // Update an author
// Author.update(1, "New Name", Date.valueOf("1966-01-01"));

// // Delete an author
// Author.delete(1);
