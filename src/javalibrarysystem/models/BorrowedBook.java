package javalibrarysystem.models;

import java.sql.*;

import javalibrarysystem.DB;
import javax.swing.table.DefaultTableModel;

public class BorrowedBook {
    private int id;
    private int bookId;
    private int userId;
    private Date borrowDate; // java.sql.Date

    // Constructor, getters and setters
    // ...

    // Method to get all borrowed books
    public static void getAll(DefaultTableModel tableModel) {
    DB db = new DB();
    ResultSet rs = null;
    Statement stmt = null;
    try {
        rs = db.executeQuery("SELECT * FROM library.borrowedbooks");
        stmt = rs.getStatement(); // Get the statement associated with this ResultSet
        while (rs.next()) {
            Object[] row = { rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getDate(4) };
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

    // Method to insert a new borrowed book entry
    public static void insert(int bookId, int userId, Date borrowDate) {
        DB db = new DB();
        String query = "INSERT INTO library.borrowedbooks (bookId, userId, borrowDate) VALUES (?, ?, ?)";
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
        String query = "SELECT * FROM library.borrowedbooks WHERE " + whereClause;
        return db.executeQuery(query);
    }

    // Method to delete a borrowed book entry by ID
    public static void delete(int id) {
        DB db = new DB();
        String query = "DELETE FROM library.borrowedbooks WHERE id = ?";
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
        String query = "UPDATE library.borrowedbooks SET bookId = ?, userId = ?, borrowDate = ? WHERE id = ?";
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