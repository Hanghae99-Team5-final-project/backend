package com.sparta.team5finalproject.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException() {
        super("유저가 존재합니다.");
    }

    public DuplicateUserException(String message) {
        super(message);
    }
}
