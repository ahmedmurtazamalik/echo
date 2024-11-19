package app.studentsocietyapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    private TextField editMembersField;

    @FXML
    private TextField editUsernameField;

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
    void enableEditForm(ActionEvent event) {
        // Inside the profilePane, make the displayInfo VBox and the editButton button invisible.
        // In turn, make the editForm VBox visible and the saveButton button visible.
    }

    @FXML
    void makeAnnouncement(ActionEvent event) {
        // Fetch the data that the user inputs in announcementTitleField and announcementContentArea.
        // Make a new entry in Post table in DB with the relevant data.
        // Send alerts (message) in case of successful/failed posts.
    }

    @FXML
    void makePost(ActionEvent event) {
        // Fetch the data that the user inputs in postTitleField and postContentArea.
        // Make a new entry in Post table in DB with the relevant data.
        // Send alerts (message) in case of successful/failed posts.
    }

    @FXML
    void saveProfileChanges(ActionEvent event) {
        // Edit the DB entry to save the changes in profile according to entered data in the fields.
        // Do the opposite visibility/invisibility actions of what you did for enableEditForm() function.
    }

    @FXML
    void showHomePane(ActionEvent event) {
        // Make the homePane visible, and these 6 invisible: notificationsPane, profilePane, organizeEventPane, manageMembersPane, makepostPane, makeAnnouncementPane.
    }

    @FXML
    void showMakeAnnouncementPane(ActionEvent event) {
        // Make the announcementPane visible, and the other 6 invisible.
    }

    @FXML
    void showMakePostPane(ActionEvent event) {
        // Make the makepostPane visible, and the other 6 invisible.
    }

    @FXML
    void showManageMembersPane(ActionEvent event) {
        // Make the manageMembersPane visible, and the other 6 invisible.

    }

    @FXML
    void showNotificationsPane(ActionEvent event) {
        // Make the notificationsPane visible, and the other 6 invisible.

    }

    @FXML
    void showOrganizeEventPane(ActionEvent event) {
        // Make the organizeEventPane visible, and the other 6 invisible.

    }

    @FXML
    void showProfilePane(ActionEvent event) {
        // Make the profilePane visible, and the other 6 invisible.

    }

}