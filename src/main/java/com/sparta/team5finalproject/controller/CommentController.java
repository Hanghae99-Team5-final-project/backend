package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.dto.commentDto.CommentRequestDto;
import com.sparta.team5finalproject.dto.commentDto.CommentResponseDto;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import com.sparta.team5finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    //댓글 생성 - cody
    @PostMapping("/comment/write/cody/{codyId}")
    public CommentResponseDto createCodyComment(
            @PathVariable Long codyId,
            @RequestBody CommentRequestDto commentRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createCodyComment(codyId,commentRequestDto, userDetails);
    }


    //댓글 생성 - watch
    @PostMapping("/comment/write/watch/{watchId}")
    public CommentResponseDto createWatchComment(
            @PathVariable Long watchId,
            @RequestBody CommentRequestDto commentRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createWatchComment(watchId,commentRequestDto, userDetails);
    }


    //댓글 수정
    @PutMapping("/comment/update/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId,
                              @RequestBody CommentRequestDto commentRequestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        return commentService.updateComment(commentId, commentRequestDto,userDetails);
    }


    //댓글 삭제
    @DeleteMapping("/comment/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        commentService.deletecomment(commentId,userDetails);
    }

}
