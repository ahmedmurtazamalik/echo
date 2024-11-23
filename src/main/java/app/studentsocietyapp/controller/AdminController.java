package app.studentsocietyapp.controller;

import app.studentsocietyapp.model.*;
import app.studentsocietyapp.persistence.SQLHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;

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
    private TableView<Society> allsocietiesTable;

    @FXML
    private HBox approveEventBox;

    @FXML
    private Button approveEventButton;

    @FXML
    private ComboBox<Event> approveEventComboBox;

    @FXML
    private HBox approveSocietyBox;

    @FXML
    private Button approveSocietyButton;

    @FXML
    private ComboBox<Society> approveSocietyComboBox;

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
    private ComboBox<Event> rejectEventComboBox;

    @FXML
    private HBox removeSocietyBox;

    @FXML
    private Button removeSocietyButton;

    @FXML
    private ComboBox<Society> removeSocietyComboBox;

    @FXML
    private TextArea societyDescriptionArea;

    @FXML
    private TextField societyEmailField;

    @FXML
    private TableColumn<?, ?> societyNameColumn;

    @FXML
    private TextField societyNameField;

    @FXML
    private TableColumn<Society, String> societyapprovalStatusColumn;

    @FXML
    private TextArea venueLocationField;

    @FXML
    private TextField venueNameField;

    @FXML
    private Label welcomeLabel;

    private SQLHandler sqlHandler;

    public void initialize() throws SQLException {
        this.setSqlHandler();
        this.initializeTables();
    }

    private void setSqlHandler() {
        this.sqlHandler = SQLHandler.getInstance();
    }

    private void initializeTables() throws SQLException {
        ArrayList<Society> pendingSocieties = sqlHandler.getPendingSocieties();
        ArrayList<Society> approvedSocieties = sqlHandler.getApprovedSocieties();
        ArrayList<Event> pendingEvents = sqlHandler.getPendingEvents();
        ArrayList<Society> allSocieties = sqlHandler.getAllSocieties();

        ObservableList<Society> pendingSocietiesList = FXCollections.observableArrayList(pendingSocieties);
        ObservableList<Society> approvedSocietiesList = FXCollections.observableArrayList(approvedSocieties);
        ObservableList<Event> pendingEventsList = FXCollections.observableArrayList(pendingEvents);

        approveEventComboBox.setItems(pendingEventsList);
        rejectEventComboBox.setItems(pendingEventsList);
        approveSocietyComboBox.setItems(pendingSocietiesList);
        removeSocietyComboBox.setItems(approvedSocietiesList);

        ObservableList<Society> societiesData = FXCollections.observableArrayList(allSocieties);
        societyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        societyapprovalStatusColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Society, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Society, String> param) {
                return new SimpleStringProperty(param.getValue().isApproved() ? "1" : "0");
            }
        });
        allsocietiesTable.setItems(societiesData);

    }

        @FXML
    void addSociety(ActionEvent event) {
        // Fetch the details from societyNameField, societyEmailField, societyDescriptionArea
        // Add an entry in society table with following details and approval status as 0.
        // (This just acts as creating a society profile)
        // (To completely register the society, it will still have to be approved from the manageSocietiesPane)
        String societyName = societyNameField.getText();
        String societyEmail = societyEmailField.getText();
        String societyDescription = societyDescriptionArea.getText();

        if (societyName.isEmpty() || societyEmail.isEmpty() || societyDescription.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Cannot add society.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
            return;
        }

        String username = societyName.toLowerCase().replaceAll("\\s+", "");
        System.out.println(username);
        String password = societyName.toLowerCase().replaceAll("\\s+", "") + "_pass";
        String accountType = "society"; // Account type
        int members = 0; // Initial members count
        int isApproved = 0; // Approval status (0 for not approved)

        sqlHandler.createSociety(username, password, 2, societyName, societyEmail, 0, societyDescription, 0);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Society Added");
        alert.setHeaderText("Success");
        alert.setContentText("Your post has been successfully created.");
        alert.showAndWait();

    }

    @FXML
    void addVenue(ActionEvent event) {
        // Fetch details from venueNameField, venueLocationField and add an entry in venue table with these details.
        String venueName = venueNameField.getText();
        String venueLocation = venueLocationField.getText();

        if (venueName.isEmpty() || venueLocation.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Cannot add venue.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
            return;
        }

        sqlHandler.createVenue(venueName, venueLocation);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Venue Added");
        alert.setHeaderText("Success");
        alert.setContentText("Your venue has been successfully created.");
        alert.showAndWait();
    }

    @FXML
    void approveSociety(ActionEvent event) {
        // approveSocietyComboBox will have the societies whose approval status bits are 0.
        // Fetch the selected society and set its approval status bit to 1.
        Society societyToApprove = approveSocietyComboBox.getSelectionModel().getSelectedItem();


    }

    @FXML
    void removeSociety(ActionEvent event) {
        // removeSocietyComboBox will have societies whose approval bits are 1
        // Fetch the selected society and set its approval status to 0.
        Society societyToRemove = removeSocietyComboBox.getSelectionModel().getSelectedItem();

    }

    @FXML
    void approveEvent(ActionEvent event) {
        // approveEventComboBox will have events which don't have an entry in EventScheduled table.
        // Fetch the event selected in approveEventComboBox and make relevant entry in EventScheduled table.
        Event eventToApprove = approveEventComboBox.getSelectionModel().getSelectedItem();

    }

    @FXML
    void rejectEvent(ActionEvent event) {
        // rejectEventComboBox will have same entries as approveEventComboBox
        // Here you just send an alert saying event successfully rejected or something, and don't make any entry in EventScheduled table.
        Event eventToReject = rejectEventComboBox.getSelectionModel().getSelectedItem();
    }

    // Navigation functions
    @FXML
    void showAddSocietyPane(ActionEvent event) {
        // addSocietyPane = visible
        // addVenuePane, manageEventsPane, manageSocietiesPane = invisible
        addSocietyPane.setVisible(true);
        manageEventsPane.setVisible(false);
        addVenuePane.setVisible(false);
        manageSocietiesPane.setVisible(false);
    }

    @FXML
    void showAddVenuePane(ActionEvent event) {
        // addVenuePane = visible
        // addSocietyPane, manageEventsPane, manageSocietiesPane = invisible
        addVenuePane.setVisible(true);
        manageEventsPane.setVisible(false);
        addSocietyPane.setVisible(false);
        manageSocietiesPane.setVisible(false);
    }

    @FXML
    void showManageEventsPane(ActionEvent event) {
        // manageEventsPane = visible
        // addVenuePane, addSocietyPane, manageSocietiesPane = invisible
        manageEventsPane.setVisible(true);
        addVenuePane.setVisible(false);
        addSocietyPane.setVisible(false);
        manageSocietiesPane.setVisible(false);
    }

    @FXML
    void showManageSocietiesPane(ActionEvent event) {
        // manageSocietiesPane = visible
        // addVenuePane, manageEventsPane, addSocietyPane = invisible
        manageSocietiesPane.setVisible(true);
        manageEventsPane.setVisible(false);
        addVenuePane.setVisible(false);
        addSocietyPane.setVisible(false);
    }

}
