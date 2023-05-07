package com.uio443.vglbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uio443.vglbackend.enums.HiddenStatus;
import com.uio443.vglbackend.enums.CompletionStatus;
import com.uio443.vglbackend.primarykey.UserGameId;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@IdClass(UserGameId.class)
@Table(name = "user_games")
public class UserGame {

    @Id
    @Column(nullable = false, updatable = false)
    private long igdbId;
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    private CompletionStatus completionStatus;
    private double hoursPlayed;
    private HiddenStatus hiddenStatus;
    private double rating;
    private String review;

    public UserGame () {
        this.igdbId = -1;
        this.user = null;
        this.completionStatus = CompletionStatus.Default;
        this.hoursPlayed = 0;
        this.hiddenStatus = HiddenStatus.Default;
        this.rating = -1;
        this.review = null;
    }

    public long getIgdbId() {
        return igdbId;
    }

    public void setIgdbId(Long igdbId) {this.igdbId = igdbId;}

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

    public double getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(double hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public HiddenStatus getHiddenStatus() {
        return hiddenStatus;
    }

    public void setHiddenStatus(HiddenStatus hiddenStatus) {
        this.hiddenStatus = hiddenStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
