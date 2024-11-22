package app.studentsocietyapp.controller;

import app.studentsocietyapp.model.*;
import app.studentsocietyapp.persistence.SQLHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SocietyController {

    @FXML
    private Button announceButton;

    @FXML
    private TextArea announcementContentArea;

    @FXML
    private TextField announcementTitleField;

    @FXML
    private StackPane contentStackPane;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private Label dateLabel;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private VBox displayInfo;

    @FXML
    private Button editButton;

    @FXML
    private TextArea editDescriptionField;

    @FXML
    private TextField editEmailField;

    @FXML
    private VBox editForm;

    @FXML
    private Button homeButton;

    @FXML
    private AnchorPane homePane;

    @FXML
    private ImageView logoImage;

    @FXML
    private Button makeAnnouncementButton;

    @FXML
    private AnchorPane makeAnnouncementPane;

    @FXML
    private Button makePostButton;

    @FXML
    private AnchorPane makepostPane;

    @FXML
    private Button manageMembersButton;

    @FXML
    private AnchorPane manageMembersPane;

    @FXML
    private Button notificationsButton;

    @FXML
    private AnchorPane notificationsPane;

    @FXML
    private TableView<?> notificationsTable;

    @FXML
    private Button organizeEventButton;

    @FXML
    private AnchorPane organizeEventPane;

    @FXML
    private Button postButton;

    @FXML
    private TextArea postContentArea;

    @FXML
    private TextField postTitleField;

    @FXML
    private Label profileApprovalLabel;

    @FXML
    private Button profileButton;

    @FXML
    private Label profileDescriptionLabel;

    @FXML
    private Label profileEmailLabel;

    @FXML
    private Label profileMemberCountLabel;

    @FXML
    private Label profileNameLabel;

    @FXML
    private AnchorPane profilePane;

    @FXML
    private Label profileUsernameLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Label societyNameLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private TableView<SocietyMemberRow> societyMembersTable;

    @FXML
    private TableColumn<?,?> memberNameColumn;

    @FXML
    private TableColumn<?,?> memberRoleColumn;

    @FXML
    private HBox removeMemberBox;

    @FXML
    private ComboBox<Student> removeMemberComboBox;

    @FXML
    private Button removeMemberButton;

    @FXML
    private Button manageRolesButton;

    @FXML
    private AnchorPane manageRolesPane;

    @FXML
    private TableView<SocietyMemberRow> membersListTable;

    @FXML
    private TableColumn<?,?> memberNameColumn1;

    @FXML
    private TableColumn<?,?> memberRoleColumn1;

    @FXML
    private HBox changeRoleBox;

    @FXML
    private ComboBox<Student> selectMemberComboBox;

    @FXML
    private ComboBox<String> selectRoleComboBox;

    @FXML
    private Button roleChangeConfirmButton;

    @FXML
    private VBox organizeEventForm;

    @FXML
    private TextField eventNameField;

    @FXML
    private DatePicker eventDatePicker;

    @FXML
    private TextField eventDateField;

    @FXML
    private TextField eventStartTimeField;

    @FXML
    private TextField eventEndTimeField;

    @FXML
    private TextArea eventDescriptionArea;

    @FXML
    private ComboBox eventVenueComboBox;

    @FXML
    private Button submitEventButton;

    @FXML
    private TableColumn<?,?> approvalStatusColumn;

    @FXML
    private HBox approveMemberBox;

    @FXML
    private ComboBox<Student> approveMemberComboBox;

    @FXML
    private Button approveMemberButton;

    @FXML
    private Button switchToPostsButton;

    @FXML
    private Button switchToAnnouncementsButton;

    @FXML
    private TableView<Announcement> announcementsTable;

    @FXML
    private TableColumn<?,?> announcementIDColumn;

    @FXML
    private TableColumn<?,?> announcementDateColumn;

    @FXML
    private TableColumn<?,?> announcementByColumn;

    @FXML
    private TableColumn<?,?> announcementDescriptionColumn;

    @FXML
    private TableView<Post> postsTable;

    @FXML
    private TableColumn<?,?> postIDColumn;

    @FXML
    private TableColumn<?,?> postDateColumn;

    @FXML
    private TableColumn<?,?> postByColumn;

    @FXML
    private TableColumn<?,?> postDescriptionColumn;

    @FXML
    private AnchorPane homeAnnouncements;

    @FXML
    private AnchorPane homePosts;

    private SQLHandler sqlHandler;
    private Society society;

    @FXML
    public void initialize() throws SQLException {
        this.setSqlHandler();
        announcementsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Announcement selectedAnnouncement = (Announcement) announcementsTable.getSelectionModel().getSelectedItem();
                if (selectedAnnouncement != null) {
                    openAnnouncementComments(selectedAnnouncement);
                }
            }
        });
        postsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Post selectedPost = postsTable.getSelectionModel().getSelectedItem();
                if (selectedPost != null) {
                    openPostComments(selectedPost);
                }
            }
        });
    }

    private void setSqlHandler() {
        this.sqlHandler = SQLHandler.getInstance();
    }

    public void setSocietyDetails(Society society) throws SQLException {
        this.society = society;
        updateProfileLabels();
        initializeTables();
        ArrayList<String> rolesList= new ArrayList<>();
        rolesList.add("VP");
        rolesList.add("Secretary");
        rolesList.add("Head");
        rolesList.add("Member");
        ObservableList<String> observableList = FXCollections.observableList(rolesList);
        selectRoleComboBox.setItems(observableList);
    }

    private void updateProfileLabels() throws SQLException {
        if (society != null) {
            profileNameLabel.setText("Name: " + society.getName());
            profileEmailLabel.setText("Email: " + society.getEmail());
            profileDescriptionLabel.setText("Description: " + society.getDescription());


            Account account = sqlHandler.getAccountDetails(society.getAccountId());
            if (account != null) {
                System.out.println("Account found");
                profileUsernameLabel.setText("Username: " + account.getUsername());
                welcomeLabel.setText(society.getName());
            }
        }
    }

    @FXML
    void enableEditForm(ActionEvent event) {
        editButton.setVisible(false);
        displayInfo.setVisible(false);
        editForm.setVisible(true);
        saveButton.setVisible(true);

        editEmailField.setText(society.getEmail());
        editDescriptionField.setText(society.getDescription());
    }

    @FXML
    void saveProfileChanges(ActionEvent event) {
        society.setEmail(editEmailField.getText());
        society.setDescription(editDescriptionField.getText());
        try{
            sqlHandler.updateSocietyDetails(society);
            updateProfileLabels();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        editForm.setVisible(false);
        saveButton.setVisible(false);
        editButton.setVisible(true);
        displayInfo.setVisible(true);
    }

    @FXML
    private void setComboBoxDisplay(ComboBox<Student> comboBox) {
        comboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty || student == null) {
                    setText(null);
                } else {
                    setText(student.getName());
                }
            }
        });

        comboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty || student == null) {
                    setText(null);
                } else {
                    setText(student.getName());
                }
            }
        });
    }

    private void initializeTables() {
        // Fetch posts and announcements data
        ArrayList<Announcement> announcements = sqlHandler.getAllAnnouncements();
        ArrayList<Post> posts = sqlHandler.getAllPosts();

        // Fetch society members and their corresponding student details
        ArrayList<SocietyMember> members = sqlHandler.getSocietyMembers(society);
        ArrayList<Venue> venues = sqlHandler.getVenues();
        ArrayList<Student> students = new ArrayList<>();

        try {
            for (SocietyMember member : members) {
                students.add(sqlHandler.getStudentDetailsByID(member.getStudentId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create observable lists for all table data
        ObservableList<SocietyMemberRow> tableData = FXCollections.observableArrayList();
        ObservableList<SocietyMemberRow> membersListData = FXCollections.observableArrayList();
        ObservableList<Post> postsData = FXCollections.observableArrayList(posts);
        ObservableList<Announcement> announcementsData = FXCollections.observableArrayList(announcements);

        // Observable lists for combo boxes
        ObservableList<Student> removeMemberList = FXCollections.observableArrayList();
        ObservableList<Student> selectMemberList = FXCollections.observableArrayList();
        ObservableList<Student> approveMemberList = FXCollections.observableArrayList();
        ObservableList<Venue> venuesList = FXCollections.observableArrayList(venues);

        eventVenueComboBox.setItems(venuesList);

        // Populate society members and combo box data
        for (int i = 0; i < members.size(); i++) {
            SocietyMember member = members.get(i);
            Student student = students.get(i);

            SocietyMemberRow row = new SocietyMemberRow(student.getName(), member.getRole(), member.getStatus());
            tableData.add(row);

            SocietyMemberRow listRow = new SocietyMemberRow(student.getName(), member.getRole(), null);
            membersListData.add(listRow);

            if (member.getStatus().equalsIgnoreCase("Approved")) {
                if (!member.getRole().equalsIgnoreCase("President")) {
                    removeMemberList.add(student);
                    selectMemberList.add(student);
                }
            } else if (member.getStatus().equalsIgnoreCase("Pending")) {
                approveMemberList.add(student);
            }
        }

        // Bind columns for postsTable
        postIDColumn.setCellValueFactory(new PropertyValueFactory<>("postId"));
        postDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        postByColumn.setCellValueFactory(new PropertyValueFactory<>("accountName"));
        postDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("title")); // Short description

        // Set postsTable data
        postsTable.setItems(postsData);

        // Bind columns for announcementsTable
        announcementIDColumn.setCellValueFactory(new PropertyValueFactory<>("announcementId"));
        announcementDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        announcementByColumn.setCellValueFactory(new PropertyValueFactory<>("societyName"));
        announcementDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("title")); // Short description

        // Set announcementsTable data
        announcementsTable.setItems(announcementsData);

        // Bind columns for societyMembersTable
        memberNameColumn.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        memberRoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        approvalStatusColumn.setCellValueFactory(new PropertyValueFactory<>("approvalStatus"));

        // Set societyMembersTable data
        societyMembersTable.setItems(tableData);

        // Bind columns for membersListTable
        memberNameColumn1.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        memberRoleColumn1.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Set membersListTable data
        membersListTable.setItems(membersListData);

        // Set combo box data
        removeMemberComboBox.setItems(removeMemberList);
        selectMemberComboBox.setItems(selectMemberList);
        approveMemberComboBox.setItems(approveMemberList);

        // Configure combo boxes to display names
        setComboBoxDisplay(removeMemberComboBox);
        setComboBoxDisplay(selectMemberComboBox);
        setComboBoxDisplay(approveMemberComboBox);
    }

    @FXML
    void makePost(ActionEvent event) {
        String postTitle = postTitleField.getText();
        String postContent = postContentArea.getText();

        if (postTitle.isEmpty() || postContent.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Cannot Create Post");
            alert.setContentText("Post title and content cannot be empty.");
            alert.showAndWait();
            return;
        }

        sqlHandler.createPost(society.getAccountId(), society.getName(), postTitle, postContent);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Post Created");
        alert.setHeaderText("Success");
        alert.setContentText("Your post has been successfully created.");
        alert.showAndWait();

        initializeTables();
        postTitleField.clear();
        postContentArea.clear();
    }

    @FXML
    void makeAnnouncement(ActionEvent event) {
        String announcementTitle = announcementTitleField.getText();
        String announcementContent = announcementContentArea.getText();

        if (announcementTitle.isEmpty() || announcementContent.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Cannot Create Announcement");
            alert.setContentText("Announcement title and content cannot be empty.");
            alert.showAndWait();
            return;
        }

        // Assuming the society ID is passed to the `createPost` method for announcements
        sqlHandler.createAnnouncement(society.getSocietyId(), society.getName(), announcementTitle, announcementContent);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Announcement Created");
        alert.setHeaderText("Success");
        alert.setContentText("Your announcement has been successfully created.");
        alert.showAndWait();

        initializeTables();
        announcementTitleField.clear();
        announcementContentArea.clear();
    }

    @FXML
    void removeMember(ActionEvent event) {
        Student selectedStudent = removeMemberComboBox.getSelectionModel().getSelectedItem();

        if (selectedStudent == null) {
            System.out.println("No student selected for removal.");
            return;
        }

        try {
            sqlHandler.removeFromSociety(selectedStudent.getStudentId(), society.getSocietyId());
            initializeTables();
            System.out.println("Student successfully removed from the society.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error removing student from the society.");
        }
    }
    
    @FXML
    void updateRole (ActionEvent event) {
        String selectedRole = selectRoleComboBox.getSelectionModel().getSelectedItem();
        Student selectedStudent = selectMemberComboBox.getSelectionModel().getSelectedItem();
        if (selectedRole == null) {
            System.out.println("No role selected for removal.");
            return;
        }
        sqlHandler.updateStudentRole(selectedStudent.getStudentId(), society.getSocietyId(), selectedRole);
        initializeTables();
    }

    @FXML
    void organizeEvent (ActionEvent event) throws SQLException {
        // Fetch the data from eventNameField, eventDatePicker, eventStartTimeField, eventEndTimeField, eventDescriptionArea.
        // Make relevant entry in Event table.
        String eventName = eventNameField.getText();
        LocalDate eventDate = eventDatePicker.getValue();
        String eventStartTime = eventStartTimeField.getText();
        String eventEndTime = eventEndTimeField.getText();
        String eventDescription = eventDescriptionArea.getText();
        Venue eventVenue = (Venue) eventVenueComboBox.getSelectionModel().getSelectedItem();


        if (eventName.isEmpty() || eventDate.toString().isEmpty() || eventStartTime.isEmpty() || eventEndTime.isEmpty() || eventDescription.isEmpty() || eventVenue == null ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Cannot send event for approval.");
            alert.setContentText("Please make sure every field is properly filled and follows the format.");
            alert.showAndWait();
            return;
        }

        sqlHandler.createEvent(society.getAccountId(), eventName, eventDescription, eventVenue, eventDate, eventStartTime, eventEndTime);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Event Created");
        alert.setHeaderText("Success");
        alert.setContentText("Event has been successfully submitted for approval.");
        alert.showAndWait();

        initializeTables();
        eventNameField.clear();
        eventStartTimeField.clear();
        eventEndTimeField.clear();
        eventDescriptionArea.clear();

    }

    @FXML
    void approveStudent(ActionEvent event) {
        Student selectedStudent = approveMemberComboBox.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            System.out.println("No student selected for approval.");
            return;
        }

        sqlHandler.approveStudent(selectedStudent.getStudentId(), society.getSocietyId());
        initializeTables();
        System.out.println("Student has been approved and role updated.");
    }

    private void openAnnouncementComments(Announcement announcement) {
        ArrayList<Comment> comments = sqlHandler.getCommentsForAnnouncement(announcement.getAnnouncementId());

        StringBuilder commentsContent = new StringBuilder();
        if (comments.isEmpty()) {
            commentsContent.append("No comments available for this announcement.");
        } else {
            commentsContent.append("Comments:\n\n");
            for (int i = 0; i < comments.size(); i++) {
                commentsContent.append(i + 1).append(". ").append(comments.get(i).getContent()).append("\n");
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Comments for Announcement");
        alert.setHeaderText("Announcement: " + announcement.getTitle());
        alert.setContentText(commentsContent.toString());
        alert.showAndWait();
    }

    private void openPostComments(Post selectedPost) {
        ArrayList<Comment> comments = sqlHandler.getCommentsForPost(selectedPost.getPostId());

        StringBuilder commentsContent = new StringBuilder();
        if (comments.isEmpty()) {
            commentsContent.append("No comments available for this Post.");
        } else {
            commentsContent.append("Comments:\n\n");
            for (int i = 0; i < comments.size(); i++) {
                commentsContent.append(i + 1).append(". ").append(comments.get(i).getContent()).append("\n");
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Comments for Post");
        alert.setHeaderText("Announcement: " + selectedPost.getTitle());
        alert.setContentText(commentsContent.toString());
        alert.showAndWait();
    }

    @FXML
    void switchToPosts(ActionEvent event) {
        homeAnnouncements.setVisible(false);
        homePosts.setVisible(true);
    }

    @FXML
    void switchToAnnouncements(ActionEvent event) {
        homeAnnouncements.setVisible(true);
        homePosts.setVisible(false);
    }

    @FXML
    void showManageRolesPane(ActionEvent event) {
        manageRolesPane.setVisible(true); // Make the manageRolesPane visible
        homePane.setVisible(false);  // Make the homePane visible
        notificationsPane.setVisible(false);  // Make the notificationsPane invisible
        profilePane.setVisible(false);  // Make the profilePane invisible
        organizeEventPane.setVisible(false);  // Make the organizeEventPane invisible
        manageMembersPane.setVisible(false);  // Make the manageMembersPane invisible
        makepostPane.setVisible(false);  // Make the makepostPane invisible
        makeAnnouncementPane.setVisible(false);  // Make the makeAnnouncementPane invisible
    }

    @FXML
    void showHomePane(ActionEvent event) {
        homePane.setVisible(true);  // Make the homePane visible
        notificationsPane.setVisible(false);  // Make the notificationsPane invisible
        profilePane.setVisible(false);  // Make the profilePane invisible
        organizeEventPane.setVisible(false);  // Make the organizeEventPane invisible
        manageMembersPane.setVisible(false);  // Make the manageMembersPane invisible
        makepostPane.setVisible(false);  // Make the makepostPane invisible
        makeAnnouncementPane.setVisible(false);  // Make the makeAnnouncementPane invisible
        manageRolesPane.setVisible(false); // Make the manageRolesPane invisible
    }

    @FXML
    void showMakeAnnouncementPane(ActionEvent event) {
        homePane.setVisible(false);  // Make the homePane invisible
        notificationsPane.setVisible(false);  // Make the notificationsPane invisible
        profilePane.setVisible(false);  // Make the profilePane invisible
        organizeEventPane.setVisible(false);  // Make the organizeEventPane invisible
        manageMembersPane.setVisible(false);  // Make the manageMembersPane invisible
        makepostPane.setVisible(false);  // Make the makepostPane invisible
        makeAnnouncementPane.setVisible(true);  // Make the makeAnnouncementPane visible
        manageRolesPane.setVisible(false); // Make the manageRolesPane invisible
    }

    @FXML
    void showMakePostPane(ActionEvent event) {
        homePane.setVisible(false);  // Make the homePane invisible
        notificationsPane.setVisible(false);  // Make the notificationsPane invisible
        profilePane.setVisible(false);  // Make the profilePane invisible
        organizeEventPane.setVisible(false);  // Make the organizeEventPane invisible
        manageMembersPane.setVisible(false);  // Make the manageMembersPane invisible
        makepostPane.setVisible(true);  // Make the makepostPane visible
        makeAnnouncementPane.setVisible(false);  // Make the makeAnnouncementPane invisible
        manageRolesPane.setVisible(false); // Make the manageRolesPane invisible
    }

    @FXML
    void showManageMembersPane(ActionEvent event) {
        homePane.setVisible(false);  // Make the homePane invisible
        notificationsPane.setVisible(false);  // Make the notificationsPane invisible
        profilePane.setVisible(false);  // Make the profilePane invisible
        organizeEventPane.setVisible(false);  // Make the organizeEventPane invisible
        manageMembersPane.setVisible(true);  // Make the manageMembersPane visible
        makepostPane.setVisible(false);  // Make the makepostPane invisible
        makeAnnouncementPane.setVisible(false);  // Make the makeAnnouncementPane invisible
        manageRolesPane.setVisible(false); // Make the manageRolesPane invisible
    }

    @FXML
    void showNotificationsPane(ActionEvent event) {
        homePane.setVisible(false);  // Make the homePane invisible
        notificationsPane.setVisible(true);  // Make the notificationsPane visible
        profilePane.setVisible(false);  // Make the profilePane invisible
        organizeEventPane.setVisible(false);  // Make the organizeEventPane invisible
        manageMembersPane.setVisible(false);  // Make the manageMembersPane invisible
        makepostPane.setVisible(false);  // Make the makepostPane invisible
        makeAnnouncementPane.setVisible(false);  // Make the makeAnnouncementPane invisible
        manageRolesPane.setVisible(false); // Make the manageRolesPane invisible
    }

    @FXML
    void showOrganizeEventPane(ActionEvent event) {
        homePane.setVisible(false);  // Make the homePane invisible
        notificationsPane.setVisible(false);  // Make the notificationsPane invisible
        profilePane.setVisible(false);  // Make the profilePane invisible
        organizeEventPane.setVisible(true);  // Make the organizeEventPane visible
        manageMembersPane.setVisible(false);  // Make the manageMembersPane invisible
        makepostPane.setVisible(false);  // Make the makepostPane invisible
        makeAnnouncementPane.setVisible(false);  // Make the makeAnnouncementPane invisible
        manageRolesPane.setVisible(false); // Make the manageRolesPane invisible
    }

    @FXML
    void showProfilePane(ActionEvent event) {
        homePane.setVisible(false);  // Make the homePane invisible
        notificationsPane.setVisible(false);  // Make the notificationsPane invisible
        profilePane.setVisible(true);  // Make the profilePane visible
        organizeEventPane.setVisible(false);  // Make the organizeEventPane invisible
        manageMembersPane.setVisible(false);  // Make the manageMembersPane invisible
        makepostPane.setVisible(false);  // Make the makepostPane invisible
        makeAnnouncementPane.setVisible(false);  // Make the makeAnnouncementPane invisible
        manageRolesPane.setVisible(false); // Make the manageRolesPane invisible
    }

}