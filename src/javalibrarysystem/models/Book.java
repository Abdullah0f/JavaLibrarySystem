package javalibrarysystem.models;

import java.sql.*;

import javalibrarysystem.DB;

public class Book {
    private int id;
    private String name;
    private int userId;
    private int genreId;
    private int authorId;
    private Date publishDate; // java.sql.Date

    // Constructor, getters and setters
    // ...

    // Method to get all books
    public static ResultSet getAll() {
        DB db = new DB();
        return db.executeQuery("SELECT * FROM Books");
    }

    // Method to insert a new book
    public static void insert(String name, int userId, int genreId, int authorId, Date publishDate) {
        DB db = new DB();
        String query = "INSERT INTO Books (name, userId, genreId, authorId, publish_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, genreId);
            pstmt.setInt(4, authorId);
            pstmt.setDate(5, publishDate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a specific book
    public static ResultSet get(String whereClause) {
        DB db = new DB();
        String query = "SELECT * FROM Books WHERE " + whereClause;
        return db.executeQuery(query);
    }

    // Method to delete a book by ID
    public static void delete(int id) {
        DB db = new DB();
        String query = "DELETE FROM Books WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a book's information
    public static void update(int id, String name, int userId, int genreId, int authorId, Date publishDate) {
        DB db = new DB();
        String query = "UPDATE Books SET name = ?, userId = ?, genreId = ?, authorId = ?, publish_date = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, genreId);
            pstmt.setInt(4, authorId);
            pstmt.setDate(5, publishDate);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
