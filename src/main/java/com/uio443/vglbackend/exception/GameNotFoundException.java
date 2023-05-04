package com.uio443.vglbackend.exception;

public class GameNotFoundException extends RuntimeException{

    public GameNotFoundException() { super("Game does not exist"); }

    public GameNotFoundException(Long igdbId) {super(String.format("Game with the id %d does not exist", igdbId));}

    public GameNotFoundException(String message) {
        super(message);
    }

}
