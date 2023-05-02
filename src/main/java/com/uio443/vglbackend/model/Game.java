package com.uio443.vglbackend.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Game")
public class Game {

    @Id
    @Column(nullable = false, updatable = false)
    private Long igdbID;
    private String title;
    private String coverImgUrl;
    @ElementCollection
    private Set<Integer> genres;

    public Game () {}

    public Game(Long igdbID, String title, String coverImgUrl, Set<Integer> genres) {
        this.igdbID = igdbID;
        this.title = title;
        this.coverImgUrl = coverImgUrl;
        this.genres = genres;
    }

    public Long getIgdbID() {
        return igdbID;
    }

    public void setIgdbID(Long igdbID) {
        this.igdbID = igdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public Set<Integer> getGenres() {
        return genres;
    }

    public void setGenres(Set<Integer> genres) {
        this.genres = genres;
    }

}
