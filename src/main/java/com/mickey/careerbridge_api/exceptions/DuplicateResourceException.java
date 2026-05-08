package com.mickey.careerbridge_api.exceptions;


public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message){
        super(message);
    }
}
