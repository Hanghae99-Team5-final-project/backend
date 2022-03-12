package com.sparta.team5finalproject.dto.commentDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.team5finalproject.model.User;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private Long commentId;
    private User commentUser;
    private String commentContent;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;


}
