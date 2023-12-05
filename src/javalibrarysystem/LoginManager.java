package javalibrarysystem;

import java.sql.*;

import javalibrarysystem.models.User;

public class LoginManager {

    // Method to handle user registration
    public static boolean register(String name, String email, String password) {
        // You should hash the password here for security

        // Insert the new user into the database
        try {
            User.insert(name, email, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Registration failed
        }
    }

    // Method to handle user login
    public static boolean login(String email, String password) {
        DB db = new DB();
        String query = "SELECT * FROM library.users WHERE email = ?";

        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                // Compare the hashed password with the stored hashed password
                return checkPassword(password, storedPassword);
            }

            return false; // User not found
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Login failed
        }
    }

    private static boolean checkPassword(String plainPassword, String storedPassword) {
        return plainPassword.equals(storedPassword);
    }
}
