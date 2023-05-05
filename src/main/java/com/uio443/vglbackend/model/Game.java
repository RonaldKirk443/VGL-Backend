package com.uio443.vglbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @Column(nullable = false, updatable = false)
    private Long igdbId;
    private String title;
    private String coverImgUrl;
    @ElementCollection
    private Set<Integer> genres;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Review> gameReviews = new ArrayList<>();

    public Game () {}

    public Game(Long igdbId, String title, String coverImgUrl, Set<Integer> genres) {
        this.igdbId = igdbId;
        this.title = title;
        this.coverImgUrl = coverImgUrl;
        this.genres = genres;
    }

    public Long getIgdbId() {
        return igdbId;
    }

    public void setIgdbId(Long igdbId) {
        this.igdbId = igdbId;
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
