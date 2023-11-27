package javalibrarysystem.models;

import java.sql.*;

import javalibrarysystem.DB;

public class Genre {
    private int id;
    private String genre;

    // Constructor, getters and setters
    // ...

    // Method to get all genres
    public static ResultSet getAll() {
        DB db = new DB();
        return db.executeQuery("SELECT * FROM Genres");
    }

    // Method to insert a new genre
    public static void insert(String genre) {
        DB db = new DB();
        String query = "INSERT INTO Genres (genre) VALUES (?)";
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
        String query = "SELECT * FROM Genres WHERE " + whereClause;
        return db.executeQuery(query);
    }

    // Method to delete a genre by ID
    public static void delete(int id) {
        DB db = new DB();
        String query = "DELETE FROM Genres WHERE id = ?";
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
        String query = "UPDATE Genres SET genre = ? WHERE id = ?";
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
