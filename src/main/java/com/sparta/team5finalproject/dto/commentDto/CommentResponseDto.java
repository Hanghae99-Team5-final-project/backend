package com.sparta.team5finalproject.dto.commentDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    // 댓글 아이디
    private Long commentId;
    // 댓글 작성한 유저
    private String commentUser;
    // 댓글 내용
    private String commentContent;
    // 댓글 생성날짜
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
}
