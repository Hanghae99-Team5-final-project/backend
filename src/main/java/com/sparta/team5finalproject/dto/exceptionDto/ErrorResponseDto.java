package com.sparta.team5finalproject.dto.exceptionDto;

import org.springframework.http.HttpStatus;

public class ErrorResponseDto extends DefaultResponseDto {

    public ErrorResponseDto(int statusCode, String responseMessage) {
        super(statusCode, responseMessage);
    }

    // BAD_REQUEST 시
    public static ErrorResponseDto badRequest(String message) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), message);
    }

    // FORBIDDEN 시
    public static ErrorResponseDto forbidden(String message) {
        return new ErrorResponseDto(HttpStatus.FORBIDDEN.value(), message);
    }

    // NOT_FOUND 시
    public static ErrorResponseDto notFound(String message) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), message);
    }

    // UNAUTHORIZED 시
    public static ErrorResponseDto unauthorized(String message) {
        return new ErrorResponseDto(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
