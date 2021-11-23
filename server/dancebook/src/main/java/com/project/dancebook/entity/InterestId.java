package com.project.dancebook.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class InterestId implements Serializable {

    private long userId;
    private long eventId;

    public InterestId() {}

    public InterestId(long userId, long eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
