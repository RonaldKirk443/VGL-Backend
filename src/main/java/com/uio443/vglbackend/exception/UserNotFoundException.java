package com.uio443.vglbackend.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User does not exist");
    }

    public UserNotFoundException(Long userId) {
        super(String.format("User with the id %d does not exist", userId));
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
