package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.dto.commentDto.CommentRequestDto;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.repository.UserRepository;
import com.sparta.team5finalproject.security.UserDetailsImpl;
import com.sparta.team5finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final UserRepository userRepository;

//    //댓글 생성
//    @PostMapping("/comment/write/")
//    public void createComment(
//            @RequestBody CommentRequestDto commentRequestDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails){
//            commentService.createComment(commentRequestDto, userDetails);
//    }

    ////////////////////////////////////시큐리티 없이 테스트용///////////////////////////////////////////////////////////
    //댓글 생성
    @PostMapping("/comment/write")
    public void createComment(
            @RequestBody CommentRequestDto commentRequestDto){
        System.out.println("11111111111111111111111");
        User userDetails;
        Optional<User> optUser = userRepository.findByUsername("bbb");
        userDetails = optUser.get();
        System.out.println("222222222222222222222="+userDetails.getUsername());
        commentService.createComment(commentRequestDto, userDetails);
    }
    ////////////////////////////////////시큐리티 없이 테스트용///////////////////////////////////////////////////////////




    //댓글 수정
    @PutMapping("/comment/update/{commentId}")
    public void updateComment(@PathVariable Long commentId,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.updateComment(commentId, commentRequestDto,userDetails);
    }

    @DeleteMapping("/comment/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        commentService.deletecomment(commentId,userDetails);
    }

}
