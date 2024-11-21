package app.studentsocietyapp.persistence;

import app.studentsocietyapp.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("batch"),
                            rs.getString("rollnumber"),
                            rs.getString("phone"),
                            rs.getInt("account_id")
                    );
                }
            }
        }
        return null; // No student found with the provided account_id
    }

    public Student getStudentDetailsByID(int student_id) throws SQLException {
        String query = "SELECT * FROM Student WHERE student_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, student_id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("batch"),
                            rs.getString("rollnumber"),
                            rs.getString("phone"),
                            rs.getInt("account_id")
                    );
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
                    return new Account(
                            rs.getInt("account_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("accountType")
                    );
                }
            }
        }
        return null; // No account found with the provided account_id
    }

    public ArrayList<Society> getStudentSocieties(int studentId) throws SQLException {
        String query = "SELECT * " +
                "FROM Society s " +
                "INNER JOIN SocietyMember sm ON sm.society_id = s.society_id " +
                "WHERE sm.student_id = ? AND sm.status = 'Approved'"; // Only approved societies
        ArrayList<Society> societies = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Society society = new Society(
                            rs.getInt("society_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getInt("members"),
                            rs.getString("description"),
                            rs.getBoolean("isApproved"),
                            rs.getInt("account_id")
                    );
                    societies.add(society);
                }
            }
        }
        return societies;
    }

    public SocietyMember getSocietyMember(int studentId, int societyId) throws SQLException {
        String query = "SELECT sm.member_id, sm.role, sm.society_id, sm.student_id, sm.status " +
                "FROM SocietyMember sm " +
                "WHERE sm.student_id = ? AND sm.society_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, societyId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new SocietyMember(
                            rs.getInt("member_id"),
                            rs.getString("role"),
                            rs.getInt("society_id"),
                            rs.getInt("student_id"),
                            rs.getString("status")
                    );
                }
            }
        }
        return null; // Return null if no SocietyMember found for the role
    }

    public ArrayList<SocietyRole> getStudentSocietyRoles(int studentId) throws SQLException {
        String query = "SELECT s.society_id, s.name, s.email, s.members, s.description, s.isApproved, s.account_id, " +
                "sm.role " +
                "FROM Society s " +
                "INNER JOIN SocietyMember sm ON sm.society_id = s.society_id " +
                "WHERE sm.student_id = ? AND sm.status = 'Approved'"; // Only approved societies

        ArrayList<SocietyRole> societyRoles = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Create Society object
                    Society society = new Society(
                            rs.getInt("society_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getInt("members"),
                            rs.getString("description"),
                            rs.getBoolean("isApproved"),
                            rs.getInt("account_id")
                    );

                    // Get the role from the result set
                    String role = rs.getString("role");

                    // Create a SocietyRole object and add it to the list
                    SocietyRole societyRole = new SocietyRole(society.getName(), role);
                    societyRoles.add(societyRole);
                }
            }
        }
        return societyRoles;
    }

    public SocietyRole getStudentSocietyRole(int studentId, int societyId) throws SQLException {
        String query = "SELECT s.society_id, s.name, s.email, s.members, s.description, s.isApproved, s.account_id, " +
                "sm.role " +
                "FROM Society s " +
                "INNER JOIN SocietyMember sm ON sm.society_id = s.society_id " +
                "WHERE sm.student_id = ? AND sm.society_id = ? AND sm.status = 'Approved'"; // Only approved societies

        SocietyRole societyRole = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, societyId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Create Society object
                    Society society = new Society(
                            rs.getInt("society_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getInt("members"),
                            rs.getString("description"),
                            rs.getBoolean("isApproved"),
                            rs.getInt("account_id")
                    );

                    // Get the role from the result set
                    String role = rs.getString("role");

                    // Create a SocietyRole object and add it to the list
                    societyRole = new SocietyRole(society.getName(), role);
                }
            }
        }
        return societyRole;
    }

    public void applyToSociety(int studentId, String societyName, String role, String comments) throws SQLException {
        // Step 1: Get the society_id using the GetAllSocieties procedure
        String getSocietyIdQuery = "{CALL GetAllSocieties()}";
        int societyId = -1;

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(getSocietyIdQuery)) {

            try (ResultSet rs = stmt.executeQuery()) {
                // Find the society_id for the given societyName
                while (rs.next()) {
                    String name = rs.getString("name");
                    if (name.equalsIgnoreCase(societyName)) {
                        societyId = rs.getInt("society_id");
                        break;
                    }
                }
                // If societyName was not found, handle the case (maybe throw an exception or return)
                if (societyId == -1) {
                    throw new SQLException("Society not found: " + societyName);
                }
            }
        }

        // Step 2: Call ApplyForRole procedure to apply for the role in the society
        String applyForRoleQuery = "{CALL ApplyForRole(?, ?, ?)}";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(applyForRoleQuery)) {

            stmt.setString(1, role);           // Role
            stmt.setInt(2, societyId);         // Society ID
            stmt.setInt(3, studentId);         // Student ID

            stmt.executeUpdate();
        }
    }

    public Society getSocietyDetails(int accountID) throws SQLException {
        String query = "SELECT * FROM Society WHERE account_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, accountID);  // Set the society name parameter

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // If a society with the given name exists, create and return the Society object
                    return new Society(
                            rs.getInt("society_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getInt("members"),
                            rs.getString("description"),
                            rs.getBoolean("isApproved"),
                            rs.getInt("account_id")
                    );
                }
            }
        }
        return null;  // Return null if no society with the given name was found
    }

    public Society getSocietyByName(String societyName) throws SQLException {
        String query = "SELECT * FROM Society WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, societyName);  // Set the society name parameter

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // If a society with the given name exists, create and return the Society object
                    return new Society(
                            rs.getInt("society_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getInt("members"),
                            rs.getString("description"),
                            rs.getBoolean("isApproved"),
                            rs.getInt("account_id")
                    );
                }
            }
        }
        return null;  // Return null if no society with the given name was found
    }

    // Update student details
    public void updateStudentDetails(Student student) throws SQLException {
        String query = "UPDATE Student SET name = ?, email = ?, batch = ?, rollnumber = ?, phone = ? WHERE student_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getBatch());
            stmt.setString(4, student.getRollNumber());
            stmt.setString(5, student.getPhone());
            stmt.setInt(6, student.getStudentId());
            stmt.executeUpdate();
        }
    }

    public void removeFromSociety(int studentId, int societyId) throws SQLException {
        String query = "DELETE FROM SocietyMember WHERE student_id = ? AND society_id = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, studentId);
            stmt.setInt(2, societyId);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void relinquishRoleInSociety(int studentId, int societyId) {
        // First, update the role to 'Member'
        String updateRoleProcedure = "{CALL UpdateSocietyMemberRole(?, ?, ?)}";
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(updateRoleProcedure)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, societyId);
            stmt.setString(3, "Member");
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return; // If updating the role fails, don't proceed with removing
        }
        // Then remove the student from the society
        try {
            removeFromSociety(studentId, societyId);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSocietyDetails(Society society) throws SQLException {
        String query = "UPDATE Society SET email = ?, description = ? WHERE society_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, society.getEmail());
            stmt.setString(2, society.getDescription());
            stmt.setInt(3, society.getSocietyId());
            stmt.executeUpdate();
        }
    }

    public ArrayList<SocietyMember> getSocietyMembers(Society society) {
        ArrayList<SocietyMember> members = new ArrayList<>();
        String query = "SELECT * FROM SocietyMember WHERE society_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, society.getSocietyId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create a SocietyMember object for each row in the result set
                SocietyMember member = new SocietyMember(rs.getInt("member_id"),
                        rs.getString("role"),
                        rs.getInt("society_id"),
                        rs.getInt("student_id"),
                        rs.getString("status")
                );
                // Add the member to the list
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }

    public void approveStudent(int studentId, int societyId) {
        String query = "UPDATE SocietyMember SET status = 'Approved', role = 'Member' WHERE student_id = ? AND society_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, societyId);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudentRole(int studentId, int societyId, String selectedRole) {
        String query = "{CALL UpdateSocietyMemberRole(?, ?, ?)}";
        try(Connection conn = getConnection();
            CallableStatement stmt = conn.prepareCall(query)){
            stmt.setInt(1, studentId);
            stmt.setInt(2, societyId);
            stmt.setString(3, selectedRole);
            stmt.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPost(int accountId, String name, String postTitle, String postContent) {
        String query = "CALL CreatePost (?, ?, ?, ?)";
        try(Connection conn = getConnection();
            CallableStatement stmt = conn.prepareCall(query)){
            stmt.setInt(1, accountId);
            stmt.setString(2, name);
            stmt.setString(3, postTitle);
            stmt.setString(4, postContent);
            stmt.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAnnouncement(int societyId, String name, String announcementTitle, String announcementContent) {
        String query = "CALL CreateAnnouncement (?, ?, ?, ?)";
        try(Connection conn = getConnection();
            CallableStatement stmt = conn.prepareCall(query)){
            stmt.setInt(1, societyId);
            stmt.setString(2, name);
            stmt.setString(3, announcementTitle);
            stmt.setString(4, announcementContent);
            stmt.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
