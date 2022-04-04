package com.sparta.team5finalproject.service;

import com.sparta.team5finalproject.dto.commentDto.CommentRequestDto;
import com.sparta.team5finalproject.dto.commentDto.CommentResponseDto;
import com.sparta.team5finalproject.model.*;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.CommentRepository;
import com.sparta.team5finalproject.repository.WatchRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final CodyRepository codyRepository;
    private final WatchRepository watchRepository;


    //댓글 생성 - cody
<<<<<<< HEAD
    @Transactional
    public void createCodyComment(Long codyId,CommentRequestDto commentRequestDto, UserDetailsImpl userDetails){
        System.out.println("받은 Watch Id 값 : " + codyId);
        System.out.println("등록하려는 댓글 내용 : "+commentRequestDto.getCommentContent());
        System.out.println("로그인한 유저정보 " + userDetails.getUsername());

=======
    public CommentResponseDto createCodyComment(Long codyId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
>>>>>>> f5ca9e0a95665bb5130b0012912a795c19fbf47e
        Cody cody = codyRepository.findById(codyId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시글 입니다."));
        CodyComment codyComment = new CodyComment();
        codyComment.setCommentContent(commentRequestDto.getCommentContent());
        codyComment.setUser(userDetails.getUser());
        codyComment.setCommentUsername(userDetails.getUsername());
        codyComment.setCody(cody);
<<<<<<< HEAD

        System.out.println("댓글 내용 나와라 ? 아? " + codyComment);

        try{
            commentRepository.save(codyComment);
        } catch(IllegalArgumentException e){
            // 예외처리, 로깅
            String detailMessage = String.format("코디 댓글 create 실패, Input: %s", codyComment.getCommentContent());
            logger.info(detailMessage);
            throw new IllegalArgumentException(detailMessage);
        }
    }

    //댓글 생성 - watch
    @Transactional
    public void createWatchComment(Long watchId,CommentRequestDto commentRequestDto, UserDetailsImpl userDetails){
        System.out.println("등록하려는 댓글 내용 : "+commentRequestDto.getCommentContent());
        System.out.println("로그인한 유저정보 " + userDetails.getUsername());
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                ()->new NullPointerException("존재하지 않는 시계글 입니다."));
=======
        commentRepository.save(codyComment);

        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setCommentId(codyComment.getId());
        commentResponseDto.setCommentUser(userDetails.getUsername());
        commentResponseDto.setCommentContent(commentRequestDto.getCommentContent());
        commentResponseDto.setCreatedAt(codyComment.getCreatedAt());

        return commentResponseDto;
    }

    //댓글 생성 - watch
    public CommentResponseDto createWatchComment(Long watchId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {

        Watch watch = watchRepository.findById(watchId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시글 입니다."));


>>>>>>> f5ca9e0a95665bb5130b0012912a795c19fbf47e
        WatchComment watchComment = new WatchComment();
        watchComment.setCommentContent(commentRequestDto.getCommentContent());
        watchComment.setUser(userDetails.getUser());
        watchComment.setCommentUsername(userDetails.getUsername());
        watchComment.setWatch(watch);
<<<<<<< HEAD
        System.out.println("댓글 내용 나와라 ? 아? " + watchComment);
        try{
            commentRepository.save(watchComment);
        } catch(IllegalArgumentException e){
            // 예외처리, 로깅
            String detailMessage = String.format("시계 댓글 create 실패, Input: %s", watchComment.getCommentContent());
            logger.info(detailMessage);
            throw new IllegalArgumentException(detailMessage);
        }
=======
        commentRepository.save(watchComment);

        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setCommentId(watchComment.getId());
        commentResponseDto.setCommentUser(userDetails.getUsername());
        commentResponseDto.setCommentContent(commentRequestDto.getCommentContent());
        commentResponseDto.setCreatedAt(watchComment.getCreatedAt());

        return commentResponseDto;
>>>>>>> f5ca9e0a95665bb5130b0012912a795c19fbf47e
    }

    //댓글 수정
    public CommentResponseDto updateComment(Long commentId,
                                            CommentRequestDto commentRequestDto,
                                            UserDetailsImpl userDetails) throws Exception{
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("코멘트를 찾을 수 없습니다."));

        if (!userDetails.getUsername().equals(comment.getCommentUsername())){
            throw new Exception("수정 할 권한이 없습니다.");
        }

        comment.update(commentRequestDto);
        comment.setCommentContent(commentRequestDto.getCommentContent());
        commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setCommentId(comment.getId());
        commentResponseDto.setCommentUser(userDetails.getUsername());
        commentResponseDto.setCommentContent(comment.getCommentContent());
        commentResponseDto.setCreatedAt(comment.getModifiedAt());

        return commentResponseDto;
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



<<<<<<< HEAD
    //댓글 수정
    @Transactional
    public void updateComment(Long commentId,
                              CommentRequestDto commentRequestDto,
                              UserDetailsImpl userDetails){
        Comment comment= commentRepository.findById(commentId).orElseThrow(
                ()-> new NullPointerException("코멘트를 찾을 수 없습니다."));

        comment.update(commentRequestDto);
//        comment.setCommentContent(commentRequestDto.getCommentContent());

        try{
            commentRepository.save(comment);
        } catch(IllegalArgumentException e){
            // 예외처리, 로깅
            String detailMessage = String.format("댓글 update 실패, Input: %s", comment.getCommentContent());
            logger.info(detailMessage);
            throw new IllegalArgumentException(detailMessage);
        }
    }

    //댓글 삭제
    @Transactional
    public void deletecomment(Long commentId, UserDetailsImpl userDetails) throws Exception {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("존재하지 않는 댓글입니다."));
        if (!userDetails.getUsername().equals(comment.getCommentUsername())){
            throw new Exception("삭제 할 권한이 없습니다.");
        }
        try{
            commentRepository.delete(comment);
        } catch(IllegalArgumentException e){
            // 예외처리, 로깅
            String detailMessage = String.format("댓글 delete 실패, Input: %s", comment.getCommentContent());
            logger.info(detailMessage);
            throw new IllegalArgumentException(detailMessage);
        }
    }
}
=======
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














>>>>>>> f5ca9e0a95665bb5130b0012912a795c19fbf47e
