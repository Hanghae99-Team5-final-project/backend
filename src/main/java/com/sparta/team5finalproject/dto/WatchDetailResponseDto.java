package com.sparta.team5finalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.team5finalproject.dto.commentDto.CommentResponseDto;
import com.sparta.team5finalproject.model.Likes;
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
//    private boolean existLikes;
    private List<CommentResponseDto> commentResponseDtoList;
}