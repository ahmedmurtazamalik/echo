package app.studentsocietyapp.model;

import javafx.beans.property.SimpleStringProperty;

public class SocietyRole {
    private final SimpleStringProperty societyName;
    private final SimpleStringProperty role;

    public SocietyRole(String societyName, String role) {
        this.societyName = new SimpleStringProperty(societyName);
        this.role = new SimpleStringProperty(role);
    }

    public String getSocietyName() {
        return societyName.get();
    }

    public void setSocietyName(String societyName) {
        this.societyName.set(societyName);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }
}