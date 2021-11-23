package com.project.dancebook.dto;

import com.project.dancebook.interfaces.Trimmable;

public class CommentCreationDTO implements Trimmable {

    private long commentId;
    private String text;
    private Long userId;
    private Long eventId;

    public CommentCreationDTO(){
    }

    public CommentCreationDTO(long commentId, String text, Long userId, Long eventId) {
        this.commentId = commentId;
        this.text = text;
        this.userId = userId;
        this.eventId = eventId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    @Override
    public void trimColumns() {
        setText(this.text.trim());
    }
}
