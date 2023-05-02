package com.uio443.vglbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_games")
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long IGDB_ID;
    private String completionStatus;
    private double hoursPlayed;
    private boolean hiddenStatus;
    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    public UserGame () {}

    public UserGame(long IGDB_ID, String completionStatus, double hoursPlayed, boolean hiddenStatus, User user) {
        this.IGDB_ID = IGDB_ID;
        this.completionStatus = completionStatus;
        this.hoursPlayed = hoursPlayed;
        this.hiddenStatus = hiddenStatus;
        this.user = user;
    }

    public long getIGDB_ID() {
        return IGDB_ID;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public double getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(double hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public boolean getHiddenStatus() {
        return hiddenStatus;
    }

    public void setHiddenStatus(boolean hiddenStatus) {
        this.hiddenStatus = hiddenStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
