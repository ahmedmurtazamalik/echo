package app.studentsocietyapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    private TableView<?> mySocietiesTable;

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
    private ComboBox<?> societylistComboBox;

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


    // Make an initializer that loads the relevant/necessary information about the currently logged in Student into global variables.
    // In the displayInfo VBox in profilePane, assign text to the labels based on the data you pulled in the initializer function.
    // In the initializer function, you will also load the list of societies the student is currently a member of into the mySocietiesTable
    // e.g name label would become "Name: Murtaza" etc.

    @FXML
    void applyToSociety(ActionEvent event) {
        // Fetch the data that the user inputs in societyNameField, roleField, and commentsField.
        // Make a new entry in SocietyMember table with isApproved bit 0 for now.
        // An attribute for commentsField in the DB may or may not be added. Up to you. I'll remove the commentsField if you decide against it.
        // Send alerts (message) in case of successful/failed applications.
    }

    @FXML
    void enableEditForm(ActionEvent event) {
        // Inside the profilePane, make the displayInfo VBox and the editButton button invisible.
        // In turn, make the editForm VBox visible and the saveButton button visible.
    }

    @FXML
    void makePost(ActionEvent event) {
        // Fetch the data that the user inputs in postTitleField and postContentField.
        // Make a new entry in Post table in DB with the relevant data.
        // Send alerts (message) in case of successful/failed posts.
    }

    @FXML
    void saveProfileChanges(ActionEvent event) {
        // Do the opposite actions of what you did for enableEditForm() function.
    }

    @FXML
    void showApplyToSocietyPane(ActionEvent event) {
        // Make the applysocietyPane visible, and all these panes invisible: homePane, notificationsPane, profilePane, mySocietiesPane, makepostPane.
    }

    @FXML
    void showHomePane(ActionEvent event) {
        // Make the homePane visible, and the other 5 invisible.
    }

    @FXML
    void showMakePostPane(ActionEvent event) {
        // Make the makepostPane visible, and the other 5 invisible.
    }

    @FXML
    void showMySocietiesPane(ActionEvent event) {
        // Make the mysocietiesPane visible, and the other 5 invisible.
    }

    @FXML
    void showNotificationsPane(ActionEvent event) {
        // Make the notificationsPane visible, and the other 5 invisible.
    }

    @FXML
    void showProfilePane(ActionEvent event) {
        // Make the profilePane visible, and the other 5 invisible.
    }

    @FXML
    void leaveaSociety(ActionEvent event) {
        // Fetch the name of the society the user selected from the societyListComboBox.
        // Check if the student's role in that society is higher than a member (we only have "member" "head" "secretary" "vp" "president" as roles so basically anything other than member).
        // If not higher than a member, handle logic for successful removal of the student from the selected society, and refresh the data in mySocietiesTable and societyListComboBox.
        // If higher, Make leaveSocietyBox invisible, and make relinquishRoleBox visible.
    }

    @FXML
    void acceptSocietyLeave(ActionEvent event) {
        // Handle logic for now successful removal of the student from the selected society.
        // Refresh the data in mySocietiesTable and societyListComboBox.
        // Send an alert saying leave successful if need be.
        // Make leaveSocietyBox visible, and relinquishRoleBox invisible.
    }

    @FXML
    void rejectSocietyLeave(ActionEvent event) {
        // Send an alert saying leave unsuccessful or rejected or something.
        // Make leaveSocietyBox visible, and relinquishRoleBox invisible.
    }

}
