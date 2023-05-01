package com.uio443.vglbackend.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

import java.util.List;
@Embeddable
public class UserGameList {
    @Transient
    private List<UserGames> userGameList;

    public UserGameList() {
    }

    public UserGameList(List<UserGames> userGameList) {
        this.userGameList = userGameList;
    }

    public List<UserGames> getUserGameList() {
        return userGameList;
    }

    public void setUserGameList(List<UserGames> userGameList) {
        this.userGameList = userGameList;
    }


    public void removeGame(UserGames userGame) {
        this.userGameList.remove(userGame);
    }

    public void addGame(UserGames userGame) {
        this.userGameList.add(userGame);
    }
}
