package com.sparta.team5finalproject.dto.watchDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchDetailLikeResponseDto {
    // 시계 상세페이지 찜하기 체크
    private boolean existLikes;
    // 시계 상세페이지 찜 아이디
    private Long likeId;
}