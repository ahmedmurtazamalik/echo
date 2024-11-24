package app.studentsocietyapp.persistence;

import app.studentsocietyapp.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SQLHandler extends PersistenceHandler {

    private static final String connectionString = "jdbc:mysql://localhost:3306/echodb";
    private static final String username = "root";
    private static final String password = "";

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
        return null;
    }

    public Society getSocietyById(int societyId) throws SQLException {
        String query = "SELECT * FROM Society WHERE society_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, societyId);  // Set the society name parameter

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // If a society with the given id exists, create and return the Society object
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
        return null;
    }

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
            return;
        }
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
                SocietyMember member = new SocietyMember(rs.getInt("member_id"),
                        rs.getString("role"),
                        rs.getInt("society_id"),
                        rs.getInt("student_id"),
                        rs.getString("status")
                );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }

    public ArrayList<Society> getPendingSocieties() throws SQLException {
        String query = "SELECT * FROM Society where isApproved = 0";
        ArrayList<Society> societies = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Create a Society object for each row and add it to the list
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

        return societies;  // Return the list of societies
    }

    public ArrayList<Event> getPendingEvents() throws SQLException {
        String query = "SELECT * FROM Event where approvalStatus = 'Pending'";
        ArrayList<Event> events = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Create an Event object for each row and add it to the list
                Event event = new Event(
                        rs.getInt("event_id"),
                        rs.getInt("society_id"),
                        rs.getString("event_name"),
                        rs.getString("event_description"),
                        rs.getInt("venue_id"),
                        rs.getDate("date"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getString("approvalStatus")
                );
                events.add(event);
            }
        }

        return events;  // Return the list of events
    }


    public ArrayList<Society> getApprovedSocieties() throws SQLException {
        String query = "SELECT * FROM Society where isApproved = 1";
        ArrayList<Society> societies = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Create a Society object for each row and add it to the list
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

        return societies;  // Return the list of societies
    }

    public ArrayList<Venue> getVenues() {
        ArrayList<Venue> venues = new ArrayList<>();
        String query = "SELECT * FROM Venue";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int venueId = rs.getInt("venue_id");
                String venueName = rs.getString("venue_name");
                String location = rs.getString("location");

                Venue venue = new Venue(venueId, venueName, location);
                venues.add(venue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return venues;
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

    public void createSociety(String username, String password, int accountType, String societyName, String societyEmail, int members, String societyDescription, int isApproved) {
        String query = "CALL CreateSociety(?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = getConnection();
            CallableStatement stmt = conn.prepareCall(query)){
            stmt.setString(1, username);          // p_username
            stmt.setString(2, password);          // p_password
            stmt.setInt(3, accountType);       // p_accountType
            stmt.setString(4, societyName);       // p_name
            stmt.setString(5, societyEmail);      // p_email
            stmt.setInt(6, members);              // p_members
            stmt.setString(7, societyDescription); // p_description
            stmt.setInt(8, isApproved);           // p_isApproved
            stmt.execute();
            System.out.println("Society profile created successfully. Pending approval.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createVenue(String venueName, String location) {
        String query = "CALL CreateVenue(?, ?)";
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {
            // Set the input parameters for the procedure
            stmt.setString(1, venueName); // p_venue_name
            stmt.setString(2, location); // p_location

            // Execute the procedure
            stmt.execute();
            System.out.println("Venue added successfully.");
        } catch (SQLException e) {
            // Handle SQL exceptions
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

    // Helper method to convert a time string ("HH:MM:SS") to Timestamp
    private Timestamp convertStringToTimestamp(String timeString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            java.util.Date parsedDate = sdf.parse(timeString);
            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if there's an error parsing the time string
        }
    }

    public void createEvent(int societyId, String eventName, String eventDescription, Venue venue, LocalDate eventDate,
                            String startTimeString, String endTimeString) throws SQLException {
        String query = "{CALL CreateEvent(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = getConnection(); // Assuming getConnection() gives a valid connection
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setInt(1, societyId);
            stmt.setString(2, eventName);
            stmt.setString(3, eventDescription);
            stmt.setInt(4, venue.getVenueId());
            stmt.setDate(5, java.sql.Date.valueOf(eventDate)); // Convert LocalDate to java.sql.Date

            // Convert startTimeString and endTimeString to Timestamp
            Timestamp startTime = convertStringToTimestamp(startTimeString);
            Timestamp endTime = convertStringToTimestamp(endTimeString);

            stmt.setTimestamp(6, startTime); // `startTime` is a java.sql.Timestamp object
            stmt.setTimestamp(7, endTime); // `endTime` is a java.sql.Timestamp object

            stmt.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Comment> getCommentsForAnnouncement(int announcementId) {
        String query = "SELECT comment.comment_id, student_id, name, content, date FROM Comment " +
                "JOIN announcementcomment AS a ON Comment.comment_id = a.comment_id " +
                "WHERE a.announcement_id = ?";
        return getComments(announcementId, query);
    }

    private ArrayList<Comment> getComments(int announcementId, String query) {
        ArrayList<Comment> comments = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, announcementId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int commentId = resultSet.getInt("comment_id");
                int studentId = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("date");

                Comment comment = new Comment(commentId, studentId, content, name, date);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }

    public ArrayList<Comment> getCommentsForPost(int postId) {
        String query = "SELECT comment.comment_id, student_id, name, content, date FROM Comment JOIN postcomment AS p " +
                "ON Comment.comment_id = p.comment_id WHERE p.post_id = ?";
        return getComments(postId, query);
    }

    public ArrayList<Post> getAllPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        String query = "SELECT post_id, account_id, name, title, content, date FROM Post";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int postId = resultSet.getInt("post_id");
                int accountId = resultSet.getInt("account_id");
                String accountName = resultSet.getString("name");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("date");

                Post post = new Post(postId, accountId, accountName, title, content, date);
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public ArrayList<Announcement> getAllAnnouncements() {
        ArrayList<Announcement> announcements = new ArrayList<>();
        String query = "SELECT announcement_id, society_id, name, title, content, date FROM Announcement";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int announcementId = resultSet.getInt("announcement_id");
                int societyId = resultSet.getInt("society_id");
                String societyName = resultSet.getString("name");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("date");

                Announcement announcement = new Announcement(announcementId, societyId, societyName, title, content, date);
                announcements.add(announcement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return announcements;
    }

    public void approveSociety(int societyId) {
        String query = "UPDATE Society SET isApproved = 1 WHERE society_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, societyId); // Set the society_id parameter

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Society with ID " + societyId + " has been approved.");
            } else {
                System.out.println("No society found with ID " + societyId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSociety(int societyId) {
        String query = "UPDATE Society SET isApproved = 0 WHERE society_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, societyId); // Set the society_id parameter

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Society with ID " + societyId + " has been removed.");
            } else {
                System.out.println("No society found with ID " + societyId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVenue(int venueId) {
        String query = "DELETE from Venue WHERE venue_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, venueId); // Set the society_id parameter

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Venue with ID " + venueId + " has been removed.");
            } else {
                System.out.println("No venue found with ID " + venueId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void approveEvent(int eventId) {
        String query = "UPDATE Event SET approvalStatus = 'Approved' WHERE event_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, eventId); // Set the society_id parameter

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Event with ID " + eventId + " has been approved.");
            } else {
                System.out.println("No event found with ID " + eventId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rejectEvent(int eventId) {
        String query = "UPDATE Event SET approvalStatus = 'Rejected' WHERE event_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, eventId); // Set the society_id parameter

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Event with ID " + eventId + " has been rejected.");
            } else {
                System.out.println("No event found with ID " + eventId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Society> getAllSocieties() {
        ArrayList<Society> societies = new ArrayList<>();
        String query = "SELECT * FROM Society";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int societyId = resultSet.getInt("society_id");
                String societyName = resultSet.getString("name");
                String email = resultSet.getString("email");
                int members = resultSet.getInt("members");
                String description = resultSet.getString("description");
                boolean isApproved = resultSet.getBoolean("isApproved");
                int accountId = resultSet.getInt("account_id");

                Society society = new Society(societyId, societyName, email, members, description, isApproved, accountId);
                societies.add(society);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return societies;
    }

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        String query = "SELECT * FROM Event";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int eventId = resultSet.getInt("event_id");
                int societyId = resultSet.getInt("society_id");
                String eventName = resultSet.getString("event_name");
                String eventDescription = resultSet.getString("event_description");
                int venueId = resultSet.getInt("venue_id");
                Date date = resultSet.getDate("date");
                Timestamp startTime = resultSet.getTimestamp("start_time");
                Timestamp endTime = resultSet.getTimestamp("end_time");
                String approvalStatus = resultSet.getString("approvalStatus");

                Event event = new Event(eventId, societyId, eventName, eventDescription, venueId, date, startTime, endTime, approvalStatus);
                events.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void makeAnnouncementComment(int studentId, int announcementId, String name, String commentText) {
        String query = "{CALL CreateAnnouncementComment(?, ?, ?, ?, ?, ?)}";

        try (Connection connection = getConnection();
             CallableStatement stmt = connection.prepareCall(query)) {

            Date currentDate = new Date(System.currentTimeMillis());

            stmt.setInt(1, 0);
            stmt.setInt(2, studentId);
            stmt.setInt(3, announcementId);
            stmt.setString(4, commentText);
            stmt.setString(5, name);
            stmt.setTimestamp(6, new Timestamp(currentDate.getTime()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void makePostComment(int studentId, int postId, String name, String commentText) {
        String query = "{CALL CreatePostComment(?, ?, ?, ?, ?, ?)}";

        try (Connection connection = getConnection();
             CallableStatement stmt = connection.prepareCall(query)) {

            Date currentDate = new Date(System.currentTimeMillis());
            System.out.println(commentText);
            stmt.setInt(1, 0);
            stmt.setInt(2, studentId);
            stmt.setInt(3, postId);
            stmt.setString(4, commentText);
            stmt.setString(5, name);
            stmt.setTimestamp(6, new Timestamp(currentDate.getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
