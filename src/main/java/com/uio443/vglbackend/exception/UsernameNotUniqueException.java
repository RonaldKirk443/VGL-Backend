package com.uio443.vglbackend.exception;

public class UsernameNotUniqueException extends RuntimeException{

    public UsernameNotUniqueException() {
        super("A user with this username already exists");
    }

    public UsernameNotUniqueException(String message) {
        super(message);
    }
}
