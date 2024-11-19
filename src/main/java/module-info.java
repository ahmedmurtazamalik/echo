module app.studentsocietyapp {
        requires javafx.controls;
        requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    exports app.studentsocietyapp;

        opens app.studentsocietyapp to
        javafx.fxml;

        opens app.studentsocietyapp.controller to javafx.fxml;

}