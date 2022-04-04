package com.sparta.team5finalproject.exception;

public class UnmatchUserException extends RuntimeException {

    public UnmatchUserException() {
        super("유저가 일치하지않습니다.");
    }

    public UnmatchUserException(String message) {
        super(message);
    }
}
