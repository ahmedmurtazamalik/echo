module app.studentsocietyapp {
        requires javafx.controls;
        requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    exports app.studentsocietyapp;

    opens app.studentsocietyapp.controller to javafx.fxml;

        exports app.studentsocietyapp.model; // Export the model package
        opens app.studentsocietyapp.model to javafx.base;
    opens app.studentsocietyapp to javafx.base, javafx.fxml; // Open the model package to javafx.base
}