package app.studentsocietyapp.model;

import java.util.Date;

public class Comment {
    private int commentId;
    private int studentId;
    private String content;
    private String studentName;
    private Date date;

    public Comment(int commentId, int studentId, String content, String studentName, Date date) {
        this.commentId = commentId;
        this.studentId = studentId;
        this.content = content;
        this.date = date;
        this.studentName = studentName;
    }

    // Getters and setters
    public int getCommentId() { return commentId; }
    public void setCommentId(int commentId) { this.commentId = commentId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
}

