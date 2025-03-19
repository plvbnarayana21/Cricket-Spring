package com.example.cricket.Exception;

public class ScoreCardNotFoundException extends RuntimeException {
    public ScoreCardNotFoundException(String message) {
        super(message);
    }
}