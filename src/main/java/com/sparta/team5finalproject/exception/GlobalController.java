package com.sparta.team5finalproject.exception;

import io.jsonwebtoken.JwtException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GlobalController {

    private Logger logger = LoggerFactory.getLogger(GlobalController.class);

    // Handler설정 방법1
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> unmatchUserExceptionHandler(UnmatchUserException exception) {
        logger.error("UnmatchUserException: {}", exception.getMessage());
        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

//    // Handler설정 방법2
//    @ExceptionHandler(UnmatchUserException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String unmatchUserException(UnmatchUserException exception) {
//        logger.error("UnmatchUserException: {}", exception.getMessage());
//        return exception.getMessage();
//    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> duplicateUserExceptionHandler(DuplicateUserException exception) {
        logger.error("DuplicateUserException: {}", exception.getMessage());
        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> PreSetNotFoundExceptionHandler(PreSetNotFoundException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> jsonParseExceptionHandler(JsonParseException exception) {
        logger.error("JsonParseException: {}", exception.getMessage());
        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        logger.error("MethodArgumentNotValidException: {}", exception.getMessage());
        return new ResponseEntity<>(ErrorResponseDto.badRequest("ValidException"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> globalDateTimeParseExceptionHandler(DateTimeParseException exception) {
        logger.error("DateTimeParseException: {}", exception.getMessage());
        return new ResponseEntity<>(ErrorResponseDto.badRequest("Date Type Error"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> globalJwtExceptionHandler(JwtException exception) {
        logger.error("JwtException: {}", exception.getMessage());
        return new ResponseEntity<>(ErrorResponseDto.unauthorized(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }


//    @ExceptionHandler
//    public ResponseEntity<ErrorResponseDto> globalRedisConnectionExceptionHandler(RedisConnectionException exception) {
//        return new ResponseEntity<>(ErrorResponseDto.badRequest("Redis Connection Error"), HttpStatus.BAD_REQUEST);
//    }


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

}
