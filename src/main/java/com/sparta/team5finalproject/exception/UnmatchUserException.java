package com.sparta.team5finalproject.exception;

public class UnmatchUserException extends RuntimeException {

    // 유저 불일치시 예외처리
    // 예외 메시지 없을때 디폴트 메시지
    public UnmatchUserException() {
        super("유저가 일치하지않습니다.");
    }

    // 예외 메시지 있을때
    public UnmatchUserException(String message) {
        super(message);
    }
}
