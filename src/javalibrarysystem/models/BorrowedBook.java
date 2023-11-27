package javalibrarysystem.models;

import java.sql.*;

import javalibrarysystem.DB;

public class BorrowedBook {
    private int id;
    private int bookId;
    private int userId;
    private Date borrowDate; // java.sql.Date

    // Constructor, getters and setters
    // ...

    // Method to get all borrowed books
    public static ResultSet getAll() {
        DB db = new DB();
        return db.executeQuery("SELECT * FROM BorrowedBooks");
    }

    // Method to insert a new borrowed book entry
    public static void insert(int bookId, int userId, Date borrowDate) {
        DB db = new DB();
        String query = "INSERT INTO BorrowedBooks (bookId, userId, borrowDate) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, userId);
            pstmt.setDate(3, borrowDate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a specific borrowed book entry
    public static ResultSet get(String whereClause) {
        DB db = new DB();
        String query = "SELECT * FROM BorrowedBooks WHERE " + whereClause;
        return db.executeQuery(query);
    }

    // Method to delete a borrowed book entry by ID
    public static void delete(int id) {
        DB db = new DB();
        String query = "DELETE FROM BorrowedBooks WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a borrowed book's information
    public static void update(int id, int bookId, int userId, Date borrowDate) {
        DB db = new DB();
        String query = "UPDATE BorrowedBooks SET bookId = ?, userId = ?, borrowDate = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, userId);
            pstmt.setDate(3, borrowDate);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
