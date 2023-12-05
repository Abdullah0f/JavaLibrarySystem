package javalibrarysystem.models;

import java.sql.*;

import javalibrarysystem.DB;
import javax.swing.table.DefaultTableModel;

public class Genre {
    private int id;
    private String genre;

    // Constructor, getters and setters
    // ...

    // Method to get all genres
    public static void getAll(DefaultTableModel tableModel) {
    DB db = new DB();
    ResultSet rs = null;
    Statement stmt = null;
    try {
        rs = db.executeQuery("SELECT * FROM library.genres");
        stmt = rs.getStatement(); // Get the statement associated with this ResultSet
        while (rs.next()) {
            Object[] row = { rs.getInt(1), rs.getString(2) };
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



    // Method to insert a new genre
    public static void insert(String genre) {
        DB db = new DB();
        String query = "INSERT INTO library.genres (genre) VALUES (?)";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, genre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a specific genre
    public static ResultSet get(String whereClause) {
        DB db = new DB();
        String query = "SELECT * FROM library.genres WHERE " + whereClause;
        return db.executeQuery(query);
    }

    // Method to delete a genre by ID
    public static void delete(int id) {
        DB db = new DB();
        String query = "DELETE FROM library.genres WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a genre's information
    public static void update(int id, String genre) {
        DB db = new DB();
        String query = "UPDATE library.genres SET genre = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, genre);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
