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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StudentController {

    @FXML
    private Button applyButton;

    @FXML
    private Button applySocietyButton;

    @FXML
    private AnchorPane applysocietyPane;

    @FXML
    private TextField commentsField;

    @FXML
    private StackPane contentStackPane;

    @FXML
    private Label dateLabel;

    @FXML
    private VBox displayInfo;

    @FXML
    private TextField editBatchField;

    @FXML
    private Button editButton;

    @FXML
    private TextField editEmailField;

    @FXML
    private VBox editForm;

    @FXML
    private TextField editNameField;

    @FXML
    private TextField editPhoneField;

    @FXML
    private TextField editRollNumberField;

    @FXML
    private TextField editUsernameField;

    @FXML
    private Button homeButton;

    @FXML
    private AnchorPane homePane;

    @FXML
    private Button leavesocietyButton;

    @FXML
    private ImageView logoImage;

    @FXML
    private Button makePostButton;

    @FXML
    private AnchorPane makepostPane;

    @FXML
    private Button mySocietiesButton;

    @FXML
    private TableView<SocietyRole> mySocietiesTable;

    @FXML
    private TableView<?> notificationsTable;

    @FXML
    private TableColumn<?,?> societynameColumn;

    @FXML
    private TableColumn<?,?> roleColumn;

    @FXML
    private TableColumn<?,?> dateColumn;

    @FXML
    private TableColumn<?,?> descriptionColumn;

    @FXML
    private AnchorPane mysocietiesPane;

    @FXML
    private Button notificationsButton;

    @FXML
    private AnchorPane notificationsPane;

    @FXML
    private Button postButton;

    @FXML
    private TextArea postContentArea;

    @FXML
    private TextField postTitleField;

    @FXML
    private Label profileBatchLabel;

    @FXML
    private Button profileButton;

    @FXML
    private Label profileEmailLabel;

    @FXML
    private Label profileNameLabel;

    @FXML
    private AnchorPane profilePane;

    @FXML
    private Label profilePhoneLabel;

    @FXML
    private Label profileRollNumberLabel;

    @FXML
    private Label profileUsernameLabel;

    @FXML
    private TextField roleField;

    @FXML
    private Label rollNumberLabel;

    @FXML
    private Button saveButton;

    @FXML
    private TextField societyNameField;

    @FXML
    private ComboBox<String> societylistComboBox;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private HBox leavesocietyBox;

    @FXML
    private HBox relinquishroleBox;

    @FXML
    private Button rolerelinquishYes;

    @FXML
    private Button rolerelinquishNo;

    @FXML
    private Button switchToPostsButton;

    @FXML
    private Button switchToAnnouncementsButton;


    @FXML
    private AnchorPane homeAnnouncements;

    @FXML
    private AnchorPane homePosts;

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
    private ComboBox<Announcement> selectAnnouncementComboBox;

    @FXML
    private TextArea announcementCommentTextArea;

    @FXML
    private Button submitAnnouncementCommentButton;

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
    private ComboBox<Post> selectPostComboBox;

    @FXML
    private TextArea postCommentTextArea;

    @FXML
    private Button submitPostCommentButton;

    private SQLHandler sqlHandler;
    private Student student;

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

    public void setStudentDetails(Student student) throws SQLException {
        this.student = student;
        updateProfileLabels();
        loadMySocieties();
        initializeTables();
    }

    private void initializeTables() {
        // Fetch data from the SQL handler
        ArrayList<Announcement> announcements = sqlHandler.getAllAnnouncements();
        ArrayList<Post> posts = sqlHandler.getAllPosts();

        // Set up the posts table
        postIDColumn.setCellValueFactory(new PropertyValueFactory<>("postId"));
        postDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        postByColumn.setCellValueFactory(new PropertyValueFactory<>("accountName"));
        postDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("title")); // Using title as a short description
        postsTable.setItems(FXCollections.observableArrayList(posts));

        // Set up the announcements table
        announcementIDColumn.setCellValueFactory(new PropertyValueFactory<>("announcementId"));
        announcementDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        announcementByColumn.setCellValueFactory(new PropertyValueFactory<>("societyName"));
        announcementDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("title")); // Using title as a short description
        announcementsTable.setItems(FXCollections.observableArrayList(announcements));

        // Fill the ComboBoxes with Post IDs and Announcement IDs
        selectPostComboBox.setItems(FXCollections.observableArrayList(posts));
        selectAnnouncementComboBox.setItems(FXCollections.observableArrayList(announcements));

        // Set the text of the ComboBox to show postId and announcementId
        selectPostComboBox.setCellFactory(param -> new ListCell<Post>() {
            @Override
            protected void updateItem(Post item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(String.valueOf(item.getPostId())); // Display the postId
                } else {
                    setText(null);
                }
            }
        });

        selectAnnouncementComboBox.setCellFactory(param -> new ListCell<Announcement>() {
            @Override
            protected void updateItem(Announcement item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(String.valueOf(item.getAnnouncementId())); // Display the announcementId
                } else {
                    setText(null);
                }
            }
        });

        // Set the prompt text for the ComboBoxes to indicate what they are for
        selectPostComboBox.setPromptText("Select Post ID");
        selectAnnouncementComboBox.setPromptText("Select Announcement ID");
    }

    private void updateProfileLabels() throws SQLException {
        if (student != null) {
            profileNameLabel.setText("Name: " + student.getName());
            profileEmailLabel.setText("Email: " + student.getEmail());
            profileBatchLabel.setText("Batch: " + student.getBatch());
            profileRollNumberLabel.setText("Roll Number: " + student.getRollNumber());
            profilePhoneLabel.setText("Phone: " + student.getPhone());

            Account account = sqlHandler.getAccountDetails(student.getAccountId());
            if (account != null) {
                profileUsernameLabel.setText("Username: " + account.getUsername());
                userNameLabel.setText(student.getName());
                welcomeLabel.setText("Welcome, " + student.getName());
            }
        }
    }

    private void loadMySocieties() throws SQLException {
        mySocietiesTable.getItems().clear();
        societylistComboBox.getItems().clear();

        ArrayList<SocietyRole> societyRoles = sqlHandler.getStudentSocietyRoles(student.getStudentId());

        societynameColumn.setCellValueFactory(new PropertyValueFactory<>("societyName"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        mySocietiesTable.getItems().addAll(societyRoles);

        for (SocietyRole societyRole : societyRoles) {
            Society society = sqlHandler.getSocietyByName(societyRole.getSocietyName());
            if (society != null) {
                societylistComboBox.getItems().add(society.getName());
            }
        }
    }

    @FXML
    void enableEditForm(ActionEvent event) {
        displayInfo.setVisible(false);
        editButton.setVisible(false);
        editForm.setVisible(true);
        saveButton.setVisible(true);

        // Pre-fill edit form with current profile values
        editNameField.setText(student.getName());
        editEmailField.setText(student.getEmail());
        editBatchField.setText(student.getBatch());
        editRollNumberField.setText(student.getRollNumber());
        editPhoneField.setText(student.getPhone());
    }

    @FXML
    void saveProfileChanges(ActionEvent event) throws SQLException {
        // Update student object with new values
        student.setName(editNameField.getText());
        student.setEmail(editEmailField.getText());
        student.setBatch(editBatchField.getText());
        student.setRollNumber(editRollNumberField.getText());
        student.setPhone(editPhoneField.getText());

        // Update database
        try {
            sqlHandler.updateStudentDetails(student);
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Entry");
        }
        // Hide edit form, show profile view
        editForm.setVisible(false);
        saveButton.setVisible(false);
        displayInfo.setVisible(true);
        editButton.setVisible(true);

        updateProfileLabels();
    }

    @FXML
    void applyToSociety(ActionEvent event) throws SQLException {
        String societyName = societyNameField.getText();
        String role = roleField.getText();
        String comments = commentsField.getText();

        if (!societyName.isEmpty() && !role.isEmpty()) {
            sqlHandler.applyToSociety(student.getStudentId(), societyName, role, comments);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Application submitted successfully!");
            alert.show();
            societyNameField.clear();
            roleField.clear();
            commentsField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all required fields.");
            alert.show();
        }
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
    void submitAnnouncementComment(ActionEvent event) {
        Announcement selectedAnnouncement = selectAnnouncementComboBox.getSelectionModel().getSelectedItem();
        String commentText = announcementCommentTextArea.getText();

        sqlHandler.makeAnnouncementComment(student.getStudentId(), selectedAnnouncement.getAnnouncementId(), student.getName(), commentText);
        announcementCommentTextArea.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Comment Submitted");
        alert.setHeaderText("Your comment has been submitted successfully.");
        alert.setContentText("Comment made on Announcement: " + selectedAnnouncement.getAnnouncementId());
        alert.showAndWait();

        System.out.println("Comment made on Announcement: " + selectedAnnouncement.getAnnouncementId());
    }


    @FXML
    void submitPostComment(ActionEvent event) {
        Post selectedPost = selectPostComboBox.getSelectionModel().getSelectedItem();
        String commentText = postCommentTextArea.getText();

        sqlHandler.makePostComment(student.getStudentId(), selectedPost.getPostId(), student.getName(), commentText);

        postCommentTextArea.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Comment Submitted");
        alert.setHeaderText("Your comment has been submitted successfully.");
        alert.setContentText("Comment made on Post: " + selectedPost.getPostId());
        alert.showAndWait();

        System.out.println("Comment made on Post: " + selectedPost.getPostId());
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
    void showProfilePane(ActionEvent event) {
        profilePane.setVisible(true);
        homePane.setVisible(false);
        applysocietyPane.setVisible(false);
        notificationsPane.setVisible(false);
        mysocietiesPane.setVisible(false);
        makepostPane.setVisible(false);
    }

    @FXML
    void showMySocietiesPane(ActionEvent event) {
        mysocietiesPane.setVisible(true);
        homePane.setVisible(false);
        applysocietyPane.setVisible(false);
        notificationsPane.setVisible(false);
        profilePane.setVisible(false);
        makepostPane.setVisible(false);
    }

    @FXML
    void showApplyToSocietyPane(ActionEvent event) {
        applysocietyPane.setVisible(true);
        homePane.setVisible(false);
        notificationsPane.setVisible(false);
        profilePane.setVisible(false);
        mysocietiesPane.setVisible(false);
        makepostPane.setVisible(false);
    }

    @FXML
    void showHomePane(ActionEvent event) {
        homePane.setVisible(true);
        applysocietyPane.setVisible(false);
        notificationsPane.setVisible(false);
        profilePane.setVisible(false);
        mysocietiesPane.setVisible(false);
        makepostPane.setVisible(false);
    }

    @FXML
    void showMakePostPane(ActionEvent event) {
        makepostPane.setVisible(true);
        homePane.setVisible(false);
        applysocietyPane.setVisible(false);
        notificationsPane.setVisible(false);
        profilePane.setVisible(false);
        mysocietiesPane.setVisible(false);
    }

    @FXML
    void showNotificationsPane(ActionEvent event) {
        notificationsPane.setVisible(true);
        homePane.setVisible(false);
        applysocietyPane.setVisible(false);
        profilePane.setVisible(false);
        mysocietiesPane.setVisible(false);
        makepostPane.setVisible(false);
    }

    @FXML
    void leaveSociety(ActionEvent event) {
        String selectedSociety = societylistComboBox.getValue();
        if (selectedSociety != null) {
            try {
                Society society = sqlHandler.getSocietyByName(selectedSociety);
                SocietyRole role = sqlHandler.getStudentSocietyRole(student.getStudentId(), society.getSocietyId());

                if (role.getRole().equals("Member")) {
                    sqlHandler.removeFromSociety(student.getStudentId(), society.getSocietyId());
                    loadMySocieties();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Successfully left the society.");
                    alert.show();
                } else {
                    leavesocietyBox.setVisible(false);
                    relinquishroleBox.setVisible(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @FXML
    public void acceptSocietyLeave(ActionEvent actionEvent) throws SQLException {
        String selectedSociety = societylistComboBox.getValue();
        Society society = sqlHandler.getSocietyByName(selectedSociety);
        sqlHandler.relinquishRoleInSociety(student.getStudentId(), society.getSocietyId());
        loadMySocieties();
        relinquishroleBox.setVisible(false);
        leavesocietyBox.setVisible(true);
    }

    @FXML
    public void rejectSocietyLeave(ActionEvent actionEvent) {
        relinquishroleBox.setVisible(false);
        leavesocietyBox.setVisible(true);
    }

    public void makePost(ActionEvent actionEvent) {
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

        sqlHandler.createPost(student.getAccountId(), student.getName(), postTitle, postContent);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Post Created");
        alert.setHeaderText("Success");
        alert.setContentText("Your post has been successfully created.");
        alert.showAndWait();

        initializeTables();

        postTitleField.clear();
        postContentArea.clear();
    }
}
