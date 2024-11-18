module app.studentsocietyapp {
        requires javafx.controls;
        requires javafx.fxml;

        exports app.studentsocietyapp;

        opens app.studentsocietyapp to
        javafx.fxml;

        opens app.studentsocietyapp.controller to javafx.fxml;

}