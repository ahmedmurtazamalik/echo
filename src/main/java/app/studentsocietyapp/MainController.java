package app.studentsocietyapp;

import app.studentsocietyapp.controller.AdminController;
import app.studentsocietyapp.controller.SocietyController;
import app.studentsocietyapp.controller.StudentController;
import app.studentsocietyapp.model.Society;
import app.studentsocietyapp.model.Student;
import app.studentsocietyapp.persistence.SQLHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class MainController {
    @FXML
    private PasswordField passwordinputfield;

    @FXML
    private Label passwordlabel;

    @FXML
    private Label signinformlabel;

    @FXML
    private Button singinbutton;

    @FXML
    private TextField usernameinputfield;

    @FXML
    private Label usernamelabel;

    private SQLHandler sqlHandler;

    @FXML
    void onSignInButtonClick(ActionEvent event) {
        String username = usernameinputfield.getText();
        String password = passwordinputfield.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showErrorAlert("Invalid Input", "Please enter both username and password.");
            return;
        }

        int accountType = -1;
        int accountID = -1;
        int[] accountDetails = {-1, -1};
        this.sqlHandler = SQLHandler.getInstance();
        // Load the second FXML file
        try {
            accountDetails = sqlHandler.authenticateUser(username, password);
        } catch (SQLException e) {
            showErrorAlert("Authentication Error", "Error during authentication. Please try again.");
            return;
        }

        accountID = accountDetails[0];
        accountType = accountDetails[1];

        if (accountType == -1) {
            showErrorAlert("Invalid Credentials", "Invalid username or password.");
        } else {
            redirectUser(event, accountType, accountID);
        }
    }

    // Redirect the user based on the account type
    private void redirectUser(ActionEvent event, int accountType, int accountID) {
        try {
            String fxmlFile = "";
            switch (accountType) {
                case 1: // Student
                    fxmlFile = "view/student-view.fxml";
                    break;
                case 2: // Society
                    fxmlFile = "view/society-view.fxml";
                    break;
                case 3: // Admin
                    fxmlFile = "view/admin-view.fxml";
                    break;
                default:
                    showErrorAlert("Unknown Account Type", "Unknown account type.");
                    return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            if (accountType == 1) {
                Student student = sqlHandler.getStudentDetails(accountID);
                // If it's a student, pass the student details to the next view (e.g., student-view.fxml)
                if (student != null) {
                    StudentController studentController = loader.getController();
                    System.out.println("Student Name: " + student.getName());
                    studentController.setStudentDetails(student);  // Passing student object to the view
                }
            } else if (accountType == 2) {
                Society society = sqlHandler.getSocietyDetails(accountID);
                if (society != null) {
                    SocietyController societyController = loader.getController();
                    System.out.println("Society Name: " + society.getName());
                    societyController.setSocietyDetails(society);
                }
            } else if (accountType == 3) {
                AdminController adminController = loader.getController();
                System.out.println("Admin Woo");
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showErrorAlert("View Load Error", "Failed to load the view.");
        } catch (SQLException e) {
            showErrorAlert("Database Error", "Failed to fetch data from the database.");
            throw new RuntimeException(e);
        }
    }

    // Helper method to show error alerts
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}