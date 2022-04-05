package com.sparta.team5finalproject.dto.codyDto;

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
    // 코디글 아이디
    private Long codyId;
    // 유저 아이디
    private Long userId;
    // 유저 이름
    private String userName;
    // 코디글 제목
    private String codyTitle;
    // 시계 브랜드
    private String watchBrand;
    // 시계 모델
    private String watchModel;
    // 코디글 내용
    private String codyContent;
    // 이미지 주소
    private String imageUrl;
    // 코디글 평점
    private String star;
    // 코디글 댓글
    private List<CommentResponseDto> commentResponseDtoList;
    // 코디글 생성날짜
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

}
