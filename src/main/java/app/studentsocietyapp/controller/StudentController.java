package app.studentsocietyapp.controller;

import app.studentsocietyapp.model.Account;
import app.studentsocietyapp.model.Student;
import app.studentsocietyapp.persistence.SQLHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;

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
    private ImageView logoImage;

    @FXML
    private Button makePostButton;

    @FXML
    private AnchorPane makepostPane;

    @FXML
    private Button mySocietiesButton;

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
    private Label userNameLabel;

    @FXML
    private Label welcomeLabel;

    // Setter for SQLHandler
    private SQLHandler sqlHandler;
    public void setSqlHandler() {
        this.sqlHandler = SQLHandler.getInstance();
    }

    // Setter for Student details
    private Student student;
    public void setStudentDetails(Student student) throws SQLException {
        this.student = student;
        updateProfileLabels();
    }

    // The initialization method will load the student information into the profile labels.
    @FXML
    public void initialize() throws SQLException {
        this.setSqlHandler();
    }

    private void updateProfileLabels() throws SQLException {
        if (student != null) {
            // Populate the profile information with the student's data.
            profileNameLabel.setText("Name: " + student.getName());
            System.out.println("Student Batch: " + student.getBatch());
            profileEmailLabel.setText("Email: " + student.getEmail());
            profileBatchLabel.setText("Batch: " + student.getBatch());
            profileRollNumberLabel.setText("Roll Number: " + student.getRollNumber());
            profilePhoneLabel.setText("Phone: " + student.getPhone());
            Account account = sqlHandler.getAccountDetails(student.getAccountId());
            if(account != null) {
                profileUsernameLabel.setText("Username: " + account.getUsername());
                userNameLabel.setText(student.getName());
                welcomeLabel.setText("Welcome, " + student.getName());
                dateLabel.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }
    }

    @FXML
    void applyToSociety(ActionEvent event) {
        // This function is not implemented yet, but it would fetch the data that the user inputs
        // in societyNameField, roleField, and commentsField, and make a new entry in the SocietyMember table.
    }

    @FXML
    void enableEditForm(ActionEvent event) {
        // Hide the displayInfo VBox and the editButton button.
        displayInfo.setVisible(false);
        editButton.setVisible(false);

        // Show the editForm VBox and the saveButton button.
        editForm.setVisible(true);
        saveButton.setVisible(true);
    }

    @FXML
    void makePost(ActionEvent event) {
        // This function is not implemented yet, but it would fetch the data that the user inputs
        // in postTitleField and postContentArea, and create a new post in the Post table.
    }

    @FXML
    void saveProfileChanges(ActionEvent event) {
        // Do the opposite actions of what you did for enableEditForm() function.
        // Hide the editForm VBox and saveButton button.
        editForm.setVisible(false);
        saveButton.setVisible(false);

        // Show the displayInfo VBox and editButton button.
        displayInfo.setVisible(true);
        editButton.setVisible(true);
    }

    @FXML
    void showApplyToSocietyPane(ActionEvent event) {
        // Make the applysocietyPane visible, and all other panes invisible.
        applysocietyPane.setVisible(true);
        homePane.setVisible(false);
        notificationsPane.setVisible(false);
        profilePane.setVisible(false);
        mysocietiesPane.setVisible(false);
        makepostPane.setVisible(false);
    }

    @FXML
    void showHomePane(ActionEvent event) {
        // Make the homePane visible, and all other panes invisible.
        homePane.setVisible(true);
        applysocietyPane.setVisible(false);
        notificationsPane.setVisible(false);
        profilePane.setVisible(false);
        mysocietiesPane.setVisible(false);
        makepostPane.setVisible(false);
    }

    @FXML
    void showMakePostPane(ActionEvent event) {
        // Make the makepostPane visible, and all other panes invisible.
        makepostPane.setVisible(true);
        homePane.setVisible(false);
        applysocietyPane.setVisible(false);
        notificationsPane.setVisible(false);
        profilePane.setVisible(false);
        mysocietiesPane.setVisible(false);
    }

    @FXML
    void showMySocietiesPane(ActionEvent event) {
        // Make the mysocietiesPane visible, and all other panes invisible.
        mysocietiesPane.setVisible(true);
        homePane.setVisible(false);
        applysocietyPane.setVisible(false);
        notificationsPane.setVisible(false);
        profilePane.setVisible(false);
        makepostPane.setVisible(false);
    }

    @FXML
    void showNotificationsPane(ActionEvent event) {
        // Make the notificationsPane visible, and all other panes invisible.
        notificationsPane.setVisible(true);
        homePane.setVisible(false);
        applysocietyPane.setVisible(false);
        profilePane.setVisible(false);
        mysocietiesPane.setVisible(false);
        makepostPane.setVisible(false);
    }

    @FXML
    void showProfilePane(ActionEvent event) {
        // Make the profilePane visible, and all other panes invisible.
        profilePane.setVisible(true);
        homePane.setVisible(false);
        applysocietyPane.setVisible(false);
        notificationsPane.setVisible(false);
        mysocietiesPane.setVisible(false);
        makepostPane.setVisible(false);
    }
}
