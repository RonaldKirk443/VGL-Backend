package com.uio443.vglbackend.exception;

public class UserGameNotFoundException extends RuntimeException{

    public UserGameNotFoundException() {
        super("User does not have this game");
    }

    public UserGameNotFoundException(Long gameId) {
        super(String.format("User does not have the game with id %d", gameId));
    }

    public UserGameNotFoundException(Long userId, Long gameId) {
        super(String.format("User %d does not have the game with id %d", userId, gameId));
    }

    public UserGameNotFoundException(String message) {
        super(message);
    }
}
