package com.sparta.team5finalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.team5finalproject.dto.commentDto.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodyResponseDto {
    private Long codyId;
    private Long userId;
    private String userName;
    private String codyTitle;
    private String watchBrand;
    private String watchModel;
    private String codyContent;
    private String imageUrl;
    private String star;
    private List<CommentResponseDto> commentResponseDtoList;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone = "Asia/Seoul")
    private LocalDateTime createdAt; // 생성날짜

}
