package javalibrarysystem.models;

import java.sql.*;

import javalibrarysystem.DB;
import javax.swing.table.DefaultTableModel;

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
    public static void getAll(DefaultTableModel tableModel) {
    DB db = new DB();
    ResultSet rs = null;
    Statement stmt = null;
    try {
        rs = db.executeQuery("SELECT * FROM library.books");
        stmt = rs.getStatement(); // Get the statement associated with this ResultSet
        while (rs.next()) {
            Object[] row = {rs.getInt(1), rs.getString(2), rs.getInt(3) ,rs.getInt(4) ,rs.getInt(5) ,rs.getDate(6)};
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

    // Method to insert a new book
    public static void insert(String name, int userId, int genreId, int authorId, Date publishDate) {
        DB db = new DB();
        String query = "INSERT INTO library.books (name, userId, genreId, authorId, publish_date) VALUES (?, ?, ?, ?, ?)";
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
        String query = "SELECT * FROM library.books WHERE " + whereClause;
        return db.executeQuery(query);
    }

    // Method to delete a book by ID
    public static void delete(int id) {
        DB db = new DB();
        String query = "DELETE FROM library.books WHERE id = ?";
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
        String query = "UPDATE library.books SET name = ?, userId = ?, genreId = ?, authorId = ?, publish_date = ? WHERE id = ?";
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
    public static boolean exists(int id) {
        DB db = new DB();
        String query = "SELECT COUNT(*) FROM library.books WHERE id = ?";
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
