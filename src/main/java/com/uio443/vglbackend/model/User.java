package com.uio443.vglbackend.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private long ID;
    private String email;
    private String username;
    @Embedded
    private UserGameList userGameList;
    private String pfpLink;

    public User() {
    }

    public User(String email, String username, UserGameList userGameList, String pfpLink) {
        this.email = email;
        this.username = username;
        this.userGameList = userGameList;
        this.pfpLink = pfpLink;
    }

    public long getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserGameList getUserGameList() {
        return userGameList;
    }

    public void setUserGameList(UserGameList userGameList) {
        this.userGameList = userGameList;
    }

    public String getPfpLink() {
        return pfpLink;
    }

    public void setPfpLink(String pfpLink) {
        this.pfpLink = pfpLink;
    }
}
