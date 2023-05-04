package com.uio443.vglbackend.primarykey;

import com.uio443.vglbackend.model.Game;
import com.uio443.vglbackend.model.User;

import java.io.Serializable;

public class ReviewId implements Serializable {

    private Game game;
    private User user;

    public ReviewId() {
    }

    public ReviewId(Game game, User user) {
        this.game = game;
        this.user = user;
    }
}
