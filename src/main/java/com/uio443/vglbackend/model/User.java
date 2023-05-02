package com.uio443.vglbackend.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String email;
    private String username;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserGame> userGameList = new ArrayList<>();
    private String pfpLink;

    public User() { }

    public User(String email, String username, List<UserGame> userGameList, String pfpLink) {
        this.email = email;
        this.username = username;
        this.userGameList = userGameList;
        this.pfpLink = pfpLink;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPfpLink() {
        return pfpLink;
    }

    public void setPfpLink(String pfpLink) {
        this.pfpLink = pfpLink;
    }

    public List<UserGame> getUserGameList() {
        return userGameList;
    }

    public void setUserGameList(List<UserGame> userGameList) {
        this.userGameList = userGameList;
    }
}
