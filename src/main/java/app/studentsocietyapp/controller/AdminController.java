package app.studentsocietyapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AdminController {

    @FXML
    private Button addSocietyButton;

    @FXML
    private AnchorPane addSocietyPane;

    @FXML
    private Button addVenueButton;

    @FXML
    private VBox addVenueForm;

    @FXML
    private AnchorPane addVenuePane;

    @FXML
    private VBox addsocietyForm;

    @FXML
    private TableView<?> allsocietiesTable;

    @FXML
    private HBox approveEventBox;

    @FXML
    private Button approveEventButton;

    @FXML
    private ComboBox<?> approveEventComboBox;

    @FXML
    private HBox approveSocietyBox;

    @FXML
    private Button approveSocietyButton;

    @FXML
    private ComboBox<?> approveSocietyComboBox;

    @FXML
    private StackPane contentStackPane;

    @FXML
    private Label dateLabel;

    @FXML
    private TableColumn<?, ?> eventDateColumn;

    @FXML
    private TableColumn<?, ?> eventEndTimeColumn;

    @FXML
    private TableColumn<?, ?> eventNameColumn;

    @FXML
    private TableColumn<?, ?> eventSocietyColumn;

    @FXML
    private TableColumn<?, ?> eventStartTimeColumn;

    @FXML
    private TableColumn<?, ?> eventStatusColumn;

    @FXML
    private TableColumn<?, ?> eventVenueButton;

    @FXML
    private TableView<?> eventsTable;

    @FXML
    private ImageView logoImage;

    @FXML
    private Button manageEventsButton;

    @FXML
    private AnchorPane manageEventsPane;

    @FXML
    private Button manageSocietiesButton;

    @FXML
    private AnchorPane manageSocietiesPane;

    @FXML
    private HBox rejectEventBox;

    @FXML
    private Button rejectEventButton;

    @FXML
    private ComboBox<?> rejectEventComboBox;

    @FXML
    private HBox removeSocietyBox;

    @FXML
    private Button removeSocietyButton;

    @FXML
    private ComboBox<?> removeSocietyComboBox;

    @FXML
    private TextArea societyDescriptionArea;

    @FXML
    private TextField societyEmailField;

    @FXML
    private TableColumn<?, ?> societyNameColumn;

    @FXML
    private TextField societyNameField;

    @FXML
    private TableColumn<?, ?> societyapprovalStatusColumn;

    @FXML
    private TextArea venueLocationField;

    @FXML
    private TextField venueNameField;

    @FXML
    private Label welcomeLabel;

    @FXML
    void addSociety(ActionEvent event) {
        // Fetch the details from societyNameField, societyEmailField, societyDescriptionArea
        // Add an entry in society table with following details and approval status as 0.
        // (This just acts as creating a society profile)
        // (To completely register the society, it will still have to be approved from the manageSocietiesPane)
    }

    @FXML
    void addVenue(ActionEvent event) {
        // Fetch details from venueNameField, venueLocationField and add an entry in venue table with these details.
    }

    @FXML
    void approveEvent(ActionEvent event) {
        // approveEventComboBox will have events which don't have an entry in EventScheduled table.
        // Fetch the event selected in approveEventComboBox and make relevant entry in EventScheduled table.
    }

    @FXML
    void approveSociety(ActionEvent event) {
        // approveSocietyComboBox will have the societies whose approval status bits are 0.
        // Fetch the selected society and set its approval status bit to 1.
    }

    @FXML
    void rejectEvent(ActionEvent event) {
        // rejectEventComboBox will have same entries as approveEventComboBox
        // Here you just send an alert saying event successfully rejected or something, and don't make any entry in EventScheduled table.
    }

    @FXML
    void removeSociety(ActionEvent event) {
        // removeSocietyComboBox will have societies whose approval bits are 1
        // Fetch the selected society and set its approval status to 0.
    }

    // Navigation functions
    @FXML
    void showAddSocietyPane(ActionEvent event) {
        // addSocietyPane = visible
        // addVenuePane, manageEventsPane, manageSocietiesPane = invisible
    }

    @FXML
    void showAddVenuePane(ActionEvent event) {
        // addVenuePane = visible
        // addSocietyPane, manageEventsPane, manageSocietiesPane = invisible

    }

    @FXML
    void showManageEventsPane(ActionEvent event) {
        // manageEventsPane = visible
        // addVenuePane, addSocietyPane, manageSocietiesPane = invisible

    }

    @FXML
    void showManageSocietiesPane(ActionEvent event) {
        // manageSocietiesPane = visible
        // addVenuePane, manageEventsPane, addSocietyPane = invisible

    }

}
