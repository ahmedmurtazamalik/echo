package app.studentsocietyapp.model;

import java.util.Date;

public class Announcement {
    private int announcementId;
    private int societyId;
    private String content;
    private Date date;

    public Announcement(int announcementId, int societyId, String content, Date date) {
        this.announcementId = announcementId;
        this.societyId = societyId;
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
}
