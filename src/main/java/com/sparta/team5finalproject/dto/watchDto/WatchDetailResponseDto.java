package com.sparta.team5finalproject.dto.watchDto;

import com.sparta.team5finalproject.dto.commentDto.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchDetailResponseDto {
    // 시계 Id
    private Long watchId;
    // 시계 상세 페이지 이미지
    private String watchImage;
    // 시계 상세 페이지 제목
    private String watchBrand;
    // 시계 상세 페이지 최저가
    private String lowestPrice;
    // 해당 시계 상세 페이지의 찜 받은 수
    private String likeCount;
    // 해당 시계의 댓글 리스트
    private List<CommentResponseDto> commentResponseDtoList;
}