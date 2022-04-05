package com.sparta.team5finalproject.exception;

public class DuplicateUserException extends RuntimeException {

    // 유저 중복시 예외처리
    // 예외 메시지 없을때 디폴트 메시지
    public DuplicateUserException() {
        super("유저가 존재합니다.");
    }

    // 예외 메시지 있을때
    public DuplicateUserException(String message) {
        super(message);
    }
}
