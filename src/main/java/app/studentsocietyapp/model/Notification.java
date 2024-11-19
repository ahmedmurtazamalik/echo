package app.studentsocietyapp.model;

import java.util.Date;

public class Notification {
    private int notificationId;
    private int studentId;
    private String message;
    private Date date;

    public Notification(int notificationId, int studentId, String message, Date date) {
        this.notificationId = notificationId;
        this.studentId = studentId;
        this.message = message;
        this.date = date;
    }

    // Getters and setters
    public int getNotificationId() { return notificationId; }
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
