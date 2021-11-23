package com.project.dancebook.entity;

import com.project.dancebook.interfaces.Trimmable;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User implements Trimmable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private long userId;
    @Column(name="username", unique=true)
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="clubName")
    private String clubName;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
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

    @Override
    public void trimColumns() {
        setUsername(this.username.trim());
        setPassword(this.password.trim());
        setFirstName(this.firstName.trim());
        setLastName(this.lastName.trim());
        setClubName(this.clubName.trim());
    }

}
