package com.project.dancebook.dto;

public class UserDTO {

    private long userId;
    private String firstName;
    private String lastName;
    private String clubName;

    public UserDTO(long userId, String firstName, String lastName, String clubName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.clubName = clubName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
