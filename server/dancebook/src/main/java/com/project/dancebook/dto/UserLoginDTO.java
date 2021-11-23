package com.project.dancebook.dto;

import com.project.dancebook.interfaces.Trimmable;

public class UserLoginDTO implements Trimmable {

    private String username;
    private String password;

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLoginDTO() {
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

    @Override
    public void trimColumns() {
        setUsername(this.username.trim());
        setPassword(this.password.trim());
    }
}
