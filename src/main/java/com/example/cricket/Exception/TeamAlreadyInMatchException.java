package com.example.cricket.Exception;

public class TeamAlreadyInMatchException extends RuntimeException {
    public TeamAlreadyInMatchException(String message) {
        super(message);
    }
}