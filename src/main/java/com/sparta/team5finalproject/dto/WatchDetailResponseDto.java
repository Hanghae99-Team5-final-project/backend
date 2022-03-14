package com.sparta.team5finalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class WatchDetailResponseDto {
    private Long watchId;
    private String watchImage;
    private String watchBrand;
    private String lowestPrice;
    private String likeCount;
    private List<CommentResponseDto> commentResponseDtoList;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
}
