package com.project.bloggingapis.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    private String message;
    public InvalidCredentialsException(String message){
        super(message);
        this.message=message;
    }
}
