package com.sparta.team5finalproject.dto;

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
public class WatchDetailLikeResponseDto {
    private boolean existLikes;
    private Long likeId;
}