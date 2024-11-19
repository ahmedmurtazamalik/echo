package app.studentsocietyapp.model;

public class PostComment {
    private int postCommentId;
    private int postId;
    private int commentId;

    public PostComment(int postCommentId, int postId, int commentId) {
        this.postCommentId = postCommentId;
        this.postId = postId;
        this.commentId = commentId;
    }

    // Getters and setters
    public int getPostCommentId() { return postCommentId; }
    public void setPostCommentId(int postCommentId) { this.postCommentId = postCommentId; }

    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public int getCommentId() { return commentId; }
    public void setCommentId(int commentId) { this.commentId = commentId; }
}
