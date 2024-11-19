package app.studentsocietyapp;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

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

    @FXML
    void onSignInButtonClick(ActionEvent event) {
        String username = usernameinputfield.getText();
        String password = passwordinputfield.getText();

        // Implement logic for signing into different views. For now, only student view has been created so placeholders for other 2 views can be created.
        // Sign UP logic will be tackled later.
        // Below given try-catch block switches from signin screen to student view.

        try {
            // Load the second FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/student-view.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Optionally, display an error message to the user
            System.out.println("Failed to load society-view.fxml");
        }

    }
}