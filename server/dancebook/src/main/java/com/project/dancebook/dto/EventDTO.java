package com.project.dancebook.dto;

import com.project.dancebook.entity.Event;

import java.time.LocalDateTime;
import java.util.Set;

public class EventDTO {

    private long eventId;
    private String name;
    private String description;
    private String location;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private int interestCount;
    private Set<CommentDTO> comments;

    public EventDTO(){
        eventId=-1;
        name=null;
        description=null;
        location=null;
        startAt=null;
        endAt=null;
        comments=null;
    };

    public EventDTO(long eventId, String name, String description, String location, LocalDateTime startAt, LocalDateTime endAt, int interestCount, Set<CommentDTO> comments) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.startAt = startAt;
        this.endAt = endAt;
        this.interestCount = interestCount;
        this.comments = comments;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public int getInterestCount() {
        return interestCount;
    }

    public void setInterestCount(int interestCount) {
        this.interestCount = interestCount;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }
}
