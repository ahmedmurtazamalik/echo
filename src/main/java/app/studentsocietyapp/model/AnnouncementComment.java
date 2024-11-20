package app.studentsocietyapp.model;

public class AnnouncementComment {
    private int announcementCommentId;
    private int announcementId;
    private int commentId;

    public AnnouncementComment(int announcementCommentId, int announcementId, int commentId) {
        this.announcementCommentId = announcementCommentId;
        this.announcementId = announcementId;
        this.commentId = commentId;
    }

    // Getters and setters
    public int getAnnouncementCommentId() { return announcementCommentId; }
    public void setAnnouncementCommentId(int announcementCommentId) { this.announcementCommentId = announcementCommentId; }

    public int getAnnouncementId() { return announcementId; }
    public void setAnnouncementId(int announcementId) { this.announcementId = announcementId; }

    public int getCommentId() { return commentId; }
    public void setCommentId(int commentId) { this.commentId = commentId; }
}
