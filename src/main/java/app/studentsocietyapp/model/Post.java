package app.studentsocietyapp.model;

import java.util.Date;

public class Post {
    private int postId;
    private int accountId;
    private String content;
    private Date date;

    public Post(int postId, int accountId, String content, Date date) {
        this.postId = postId;
        this.accountId = accountId;
        this.content = content;
        this.date = date;
    }

    // Getters and setters
    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
