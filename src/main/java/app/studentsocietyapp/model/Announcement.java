package app.studentsocietyapp.model;

import java.util.Date;

public class Announcement {
    private int announcementId;
    private int societyId;
    private String societyName;
    private String title;
    private String content;
    private Date date;

    public Announcement(int announcementId, int societyId, String societyName, String title, String content, Date date) {
        this.announcementId = announcementId;
        this.societyId = societyId;
        this.societyName = societyName;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    // Getters and setters
    public int getAnnouncementId() { return announcementId; }
    public void setAnnouncementId(int announcementId) { this.announcementId = announcementId; }

    public int getSocietyId() { return societyId; }
    public void setSocietyId(int societyId) { this.societyId = societyId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSocietyName() { return societyName; }
    public void setSocietyName(String societyName) { this.societyName = societyName; }
}
