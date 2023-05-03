package com.uio443.vglbackend.primarykey;

import com.uio443.vglbackend.model.User;

import java.io.Serializable;

public class UserGameId implements Serializable {
    private Long igdbId;
    private User user;

    public UserGameId() {}

    public UserGameId(Long igdbId, User user){
        this.igdbId = igdbId;
        this.user = user;
    }
}
