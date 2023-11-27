package javalibrarysystem;

import java.sql.*;

public class DB {
    // Consider defining these as constants or external configuration
    private static final String URL = "jdbc:mysql://localhost:3306/javaGUI";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Method to establish a database connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Execute a query with a ResultSet
    public ResultSet executeQuery(String query) {
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            // Handle exception: log or throw a custom exception
            e.printStackTrace();
            return null;
        }
    }

    // Execute an update (INSERT, UPDATE, DELETE)
    public int executeUpdate(String query) {
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            // Handle exception: log or throw a custom exception
            e.printStackTrace();
            return -1;
        }
    }
}
