package com.diningreview.dining.exceptions;


// to make a custom exception
public class UsernameAlreadyExistsException extends Exception {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
    
}
