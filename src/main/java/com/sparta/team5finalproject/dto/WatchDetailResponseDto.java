package com.sparta.team5finalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.team5finalproject.dto.commentDto.CommentResponseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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