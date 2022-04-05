package com.sparta.team5finalproject.dto.exceptionDto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class DefaultResponseDto {
    // 상태 코드
    private Integer statusCode;
    // 예외 메시지
    private String responseMessage;


    protected DefaultResponseDto(Integer statusCode, String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
    }

    protected DefaultResponseDto() {
    }
}