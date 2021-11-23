package com.project.dancebook.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="commentId")
    private Long commentId;
    @Column(name="text")
    private String text;
    @ManyToOne
    @JoinColumn(referencedColumnName="userId")
    private User user= new User();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(referencedColumnName="eventId")
    private Event event = new Event();
    private long createdAt = System.currentTimeMillis();

    public Comment() {}

    public Comment(String text, User user, Event event) {
        this.text = text;
        this.user = user;
        this.event = event;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
