package app.studentsocietyapp.persistence;

import app.studentsocietyapp.model.Account;
import app.studentsocietyapp.model.Student;

import java.sql.*;

public class SQLHandler extends PersistenceHandler {

    private static final String connectionString = "jdbc:mysql://localhost:3306/echodb";
    private static final String username = "root";
    private static final String password = "Inazuma11";

    private static SQLHandler instance = null;

    private SQLHandler() {}

    public static synchronized SQLHandler getInstance() {
        if (instance == null) {
            instance = new SQLHandler();
        }
        return instance;
    }

    private Connection getConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(connectionString, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found. Please ensure the driver is included in the project.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database: " + e.getMessage(), e);
        }
    }

    // Authenticate the user and return the account type and account_id
    public int[] authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT account_id, accountType FROM Account WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // Consider using hashed passwords for security

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int accountId = rs.getInt("account_id");
                    int accountType = rs.getInt("accountType"); // 1 = Student, 2 = Society, 3 = Admin
                    return new int[]{accountId, accountType};
                }
            }
        }
        return new int[]{-1, -1};  // Invalid credentials
    }

    // Fetch the details of a student using account_id
    public Student getStudentDetails(int accountId) throws SQLException {
        String query = "SELECT * FROM Student WHERE account_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, accountId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student(rs.getInt("student_id"), rs.getString("name"),
                            rs.getString("email"), rs.getString("batch"),
                            rs.getString("rollnumber"), rs.getString("phone"),
                            rs.getInt("account_id"));
                    return student;
                }
            }
        }
        return null; // No student found with the provided account_id
    }

    // Fetch the account details using account_id
    public Account getAccountDetails(int accountId) throws SQLException {
        String query = "SELECT * FROM Account WHERE account_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, accountId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                            rs.getString("password"), rs.getInt("accountType"));
                    return account;
                }
            }
        }
        return null; // No account found with the provided account_id
    }
}
