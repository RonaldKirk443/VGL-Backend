package com.uio443.vglbackend.exception;

public class EmailNotUniqueException extends RuntimeException{

    public EmailNotUniqueException(){
        super("A user with this email already exists");
    }

    public EmailNotUniqueException(String message){
        super(message);
    }
}
