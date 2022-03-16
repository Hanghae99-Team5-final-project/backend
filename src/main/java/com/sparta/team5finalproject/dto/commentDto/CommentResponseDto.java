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
public class CommentResponseDto { //CommentResponseDto
    private Long commentId;             // 댓글 ID
    private String commentUser;         // 댓글작성자
    private String commentContent;      // 댓글


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone = "Asia/Seoul")
    private LocalDateTime createdAt;   // 생성날짜
}
