package app.studentsocietyapp.controller;

import app.studentsocietyapp.model.Account;
import app.studentsocietyapp.model.Society;
import app.studentsocietyapp.model.Student;
import app.studentsocietyapp.persistence.SQLHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

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
    private TableView<?> societyMembersTable;

    @FXML
    private TableColumn<?,?> memberNameColumn;

    @FXML
    private TableColumn<?,?> memberRoleColumn;

    @FXML
    private HBox removeMemberBox;

    @FXML
    private ComboBox removeMemberComboBox;

    @FXML
    private Button removeMemberButton;

    @FXML
    private Button manageRolesButton;

    @FXML
    private AnchorPane manageRolesPane;

    @FXML
    private TableView<?> membersListTable;

    @FXML
    private TableColumn<?,?> memberNameColumn1;

    @FXML
    private TableColumn<?,?> memberRoleColumn1;

    @FXML
    private HBox changeRoleBox;

    @FXML
    private ComboBox selectMemberComboBox;

    @FXML
    private ComboBox selectRoleComboBox;

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
    private ComboBox approveMemberComboBox;

    @FXML
    private Button approveMemberButton;

    private SQLHandler sqlHandler;
    private Society society;

    @FXML
    public void initialize() throws SQLException {
        this.setSqlHandler();
    }

    private void setSqlHandler() {
        this.sqlHandler = SQLHandler.getInstance();
    }

    public void setSocietyDetails(Society society) throws SQLException {
        this.society = society;
        updateProfileLabels();
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
        // Only e-mail and description are editable.
    }

    @FXML
    void removeMember (ActionEvent event) {
        // Fetch the society member selected in removeMemberComboBox.
        // In case of any role except VP or President, removal will be successful. If it's a VP or Pres, Either (a) Send alert saying can't remove VP / President if selected member is VP or President, or (b) Just filter out the VP and President and don't load/display them in the ComboBox in the first place.
        // Do appropriate action to remove that student as a member of that soceity. I
    }

    @FXML
    void updateRole (ActionEvent event) {
        // Fetch the student selected in selectMemberComboBox.
        // Fetch the role selected in selectRoleComboBox.
        // Update the role of that student.
        // Only secretaries, heads, and members are replaceable. VP and President are not replaceable. (I think)
    }

    @FXML
    void organizeEvent (ActionEvent event) {
        // Fetch the data from eventNameField, eventDatePicker, eventStartTimeField, eventEndTimeField, eventDescriptionArea.
        // Make relevant entry in Event table.
    }

    @FXML
    void approveStudent (ActionEvent event) {
        // You will have loaded the pending members only into the approveMemberComboBox in initializer.
        // Fetch selected student from approveMemberComboBox.
        // Do appropriate action to add that student as a member of that society.
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