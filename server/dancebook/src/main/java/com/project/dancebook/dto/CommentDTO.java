package com.project.dancebook.dto;

public class CommentDTO {

    private Long commentId;
    private String text;
    private UserDTO user;
    private long eventId;
    private long createdAt;

    public CommentDTO(Long commentId, String text, UserDTO user, long eventId, long createdAt) {
        this.commentId = commentId;
        this.text = text;
        this.user = user;
        this.eventId = eventId;
        this.createdAt = createdAt;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
