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

    private SQLHandler sqlHandler;
    private Student student;

    // Initialize method
    @FXML
    public void initialize() throws SQLException {
        this.setSqlHandler();
    }

    private void setSqlHandler() {
        this.sqlHandler = SQLHandler.getInstance();
    }

    public void setStudentDetails(Student student) throws SQLException {
        this.student = student;
        updateProfileLabels();
        loadMySocieties();
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

        // Fetch the list of SocietyRole objects for the given student
        ArrayList<SocietyRole> societyRoles = sqlHandler.getStudentSocietyRoles(student.getStudentId());

        // Assuming you have columns for society name and role in your TableView
        TableColumn<SocietyRole, String> societyNameColumn = new TableColumn<>("Society Name");
        societyNameColumn.setCellValueFactory(new PropertyValueFactory<>("societyName")); // Binding to societyName in SocietyRole

        TableColumn<SocietyRole, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role")); // Binding to role in SocietyRole

        mySocietiesTable.getColumns().setAll(societyNameColumn, roleColumn);
        mySocietiesTable.getItems().addAll(societyRoles);

        // Iterate over the SocietyRole list
        for (SocietyRole societyRole : societyRoles) {
            // Fetch the Society object using the name from the SocietyRole
            Society society = sqlHandler.getSocietyByName(societyRole.getSocietyName());
            // Add the Society object to the ComboBox (if needed)
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

    // Methods for switching panes
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
//        String selectedSociety = societylistComboBox.getValue().toString();
//        if (selectedSociety != null) {
//            var societyDetails = sqlHandler.getSocietyDetails(selectedSociety);
//            String role = societyDetails.getRole(student.getStudentId());
//
//            if (role.equals("member")) {
//                sqlHandler.removeFromSociety(student.getStudentId(), selectedSociety);
//                loadMySocieties();
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setContentText("Successfully left the society.");
//                alert.show();
//            } else {
//                leavesocietyBox.setVisible(false);
//                relinquishroleBox.setVisible(true);
//            }
//        }
    }

    @FXML
    void relinquishRole(ActionEvent event) throws SQLException {
//        String selectedSociety = societylistComboBox.getValue().toString();
//        sqlHandler.relinquishRoleInSociety(student.getStudentId(), selectedSociety);
//        loadMySocieties();
//        relinquishroleBox.setVisible(false);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setContentText("Successfully relinquished role in society.");
//        alert.show();
    }

    @FXML
    void cancelRelinquishRole(ActionEvent event) {
        relinquishroleBox.setVisible(false);
    }

    public void makePost(ActionEvent actionEvent) {
    }

    public void acceptSocietyLeave(ActionEvent actionEvent) {
    }

    public void rejectSocietyLeave(ActionEvent actionEvent) {
    }

    public void leaveaSociety(ActionEvent actionEvent) {
    }
}
