package javalibrarysystem.models;

import java.sql.*;

import javalibrarysystem.DB;

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
    public static ResultSet getAll() {
        DB db = new DB();
        return db.executeQuery("SELECT * FROM Authors");
    }

    // Method to insert a new author
    public static void insert(String authorName, Date dob) {
        DB db = new DB();
        String query = "INSERT INTO Authors (author_name, DOB) VALUES (?, ?)";
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
        String query = "SELECT * FROM Authors WHERE " + whereClause;
        return db.executeQuery(query);
    }

    // Method to delete an author by ID
    public static void delete(int id) {
        DB db = new DB();
        String query = "DELETE FROM Authors WHERE id = ?";
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
        String query = "UPDATE Authors SET author_name = ?, DOB = ? WHERE id = ?";
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
