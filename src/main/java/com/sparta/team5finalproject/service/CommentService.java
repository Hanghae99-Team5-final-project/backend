package com.sparta.team5finalproject.service;

import com.sparta.team5finalproject.dto.commentDto.CommentRequestDto;
import com.sparta.team5finalproject.model.*;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.CommentRepository;
import com.sparta.team5finalproject.repository.WatchRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CodyRepository codyRepository;
    private final WatchRepository watchRepository;

    //댓글 생성 - cody
    public void createCodyComment(Long codyId,CommentRequestDto commentRequestDto, UserDetailsImpl userDetails){
        System.out.println("받은 Watch Id 값 : " + codyId);
        System.out.println("등록하려는 댓글 내용 : "+commentRequestDto.getCommentContent());
        System.out.println("로그인한 유저정보 " + userDetails.getUsername());
        Cody cody = codyRepository.findById(codyId).orElseThrow(
                ()->new NullPointerException("존재하지 않는 게시글 입니다."));
        CodyComment codyComment = new CodyComment();
        codyComment.setCommentContent(commentRequestDto.getCommentContent());
        codyComment.setUser(userDetails.getUser());
        codyComment.setCommentUsername(userDetails.getUsername());
        codyComment.setCody(cody);

        System.out.println("댓글 내용 나와라 ? 아? " + codyComment);
        commentRepository.save(codyComment);
    }

    //댓글 생성 - watch
    public void createWatchComment(Long watchId,CommentRequestDto commentRequestDto, UserDetailsImpl userDetails){
        System.out.println("등록하려는 댓글 내용 : "+commentRequestDto.getCommentContent());
        System.out.println("로그인한 유저정보 " + userDetails.getUsername());
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                ()->new NullPointerException("존재하지 않는 게시글 입니다."));
        WatchComment watchComment = new WatchComment();
        watchComment.setCommentContent(commentRequestDto.getCommentContent());
        watchComment.setUser(userDetails.getUser());
        watchComment.setCommentUsername(userDetails.getUsername());
        watchComment.setWatch(watch);
        System.out.println("댓글 내용 나와라 ? 아? " + watchComment);
        commentRepository.save(watchComment);
    }


//    ////////////////////////////////////시큐리티 없이 테스트용///////////////////////////////////////////////////////////
//    public void createComment(CommentRequestDto commentRequestDto, User user){
//        Cody cody = codyRepository.findById(commentRequestDto.getCodyId()).orElseThrow(
//                ()->new NullPointerException("존재하지 않는 게시글 입니다."));
//        CodyComment codyComment = new CodyComment();
//        codyComment.setCommentContent(commentRequestDto.getCommentContent());
//        codyComment.setUser(user);
//        codyComment.setCommentUsername(user.getUsername());
//        codyComment.setCody(cody);
//
//        commentRepository.save(codyComment);
//    }
//    ////////////////////////////////////시큐리티 없이 테스트용///////////////////////////////////////////////////////////









    //댓글 수정
    public void updateComment(Long commentId,
                              CommentRequestDto commentRequestDto,
                              UserDetailsImpl userDetails){
        Comment comment= commentRepository.findById(commentId).orElseThrow(
                ()-> new NullPointerException("코멘트를 찾을 수 없습니다."));

        comment.update(commentRequestDto);
//        comment.setCommentContent(commentRequestDto.getCommentContent());
        commentRepository.save(comment);
    }

    //댓글 삭제
    public void deletecomment(Long commentId, UserDetailsImpl userDetails) throws Exception {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("존재하지 않는 댓글입니다."));
        if (!userDetails.getUsername().equals(comment.getCommentUsername())){
            throw new Exception("삭제 할 권한이 없습니다.");
        }
        commentRepository.delete(comment);
    }
}