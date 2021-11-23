package com.project.dancebook.entity;

import com.project.dancebook.interfaces.Trimmable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
public class Event implements Trimmable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eventId")
    private long eventId;
    @Column(name="name", unique=true)
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="location")
    private String location;
    @Column(name="startAt")
    private LocalDateTime startAt;
    @Column(name="endAt")
    private LocalDateTime endAt;
    @Column(name="interestCount")
    private int interestCount=0;
    @OneToMany(mappedBy="event")
    private Set<Comment> comments= new HashSet<>();

    public Event() {}

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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public void trimColumns() {
        setLocation(this.location.trim());
        setDescription(this.description.trim());
        setName(this.name.trim());
    }
}
