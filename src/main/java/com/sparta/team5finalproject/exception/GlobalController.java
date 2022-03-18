package com.sparta.team5finalproject.exception;


import io.jsonwebtoken.JwtException;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GlobalController {

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> unmatchedUserExceptionHandler(UnmatchedUserException exception) {
        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> PreSetNotFoundExceptionHandler(PreSetNotFoundException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(ErrorResponseDto.badRequest("ValidException"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> globalDateTimeParseExceptionHandler(DateTimeParseException exception) {
        return new ResponseEntity<>(ErrorResponseDto.badRequest("Date Type Error"), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> globalRedisConnectionExceptionHandler(RedisConnectionException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.badRequest("Redis Connection Error"), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> globalJwtExceptionHandler(JwtException exception) {
        return new ResponseEntity<>(ErrorResponseDto.unauthorized(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> oAuthProviderMissMatchExceptionHandler(OAuthProviderMissMatchException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> invalidSocialNameExceptionHandler(InvalidSocialNameException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> userIdNotFoundExceptionHandler(UserIdNotFoundException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.notFound(exception.getMessage()), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> monsterNotFoundExceptionHandler(MonsterNotFoundException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.notFound(exception.getMessage()), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> HabitIdNotFoundExceptionHandler(HabitIdNotFoundException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.notFound(exception.getMessage()), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> alreadyGoalCountExceptionExceptionHandler(AlreadyGoalCountException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> invalidLevelExceptionHandler(InvalidLevelException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> invalidRecommendationTypeExceptionHandler(InvalidRecommendationTypeException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.notFound(exception.getMessage()), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> invalidCategoryExceptionHandler(InvalidCategoryException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.notFound(exception.getMessage()), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> hasNoPermissionExceptionHandler(HasNoPermissionException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.forbidden(exception.getMessage()), HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> notReachedMaximumLevelExceptionHandler(NotReachedMaximumLevelException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> jsonParseExceptionHandler(JsonParseException exception) {
        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}