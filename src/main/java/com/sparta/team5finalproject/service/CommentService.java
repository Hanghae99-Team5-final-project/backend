package com.sparta.team5finalproject.service;

<<<<<<< HEAD

import com.sparta.team5finalproject.dto.CommentRequestDto;
import com.sparta.team5finalproject.dto.CommentResopnseDto;
=======
import com.sparta.team5finalproject.dto.commentDto.CommentRequestDto;
>>>>>>> 84b6353e75f8e26db9ab1a16ecf6572eddd4bfd1
import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.CodyComment;
import com.sparta.team5finalproject.model.Comment;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.CommentRepository;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CodyRepository codyRepository;

    @Autowired
    public CommentService(
            CommentRepository commentRepository,
            CodyRepository codyRepository) {

        this.commentRepository = commentRepository;
        this.codyRepository = codyRepository;
    }

    @Transactional
    public CodyComment writeComment(CommentRequestDto commentRequestDto, Long codyId) {
        Cody cody = commentRepository.findById(codyId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다"));
        CodyComment codyComment = new CodyComment(commentRequestDto,cody);

        commentRepository.save(codyComment);
    }

=======
import com.sparta.team5finalproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CodyRepository codyRepository;

//    //댓글 생성
//    public void createComment(CommentRequestDto commentRequestDto, UserDetailsImpl userDetails){
//        Cody cody = codyRepository.findById(commentRequestDto.getCodyId()).orElseThrow(
//                ()->new NullPointerException("존재하지 않는 게시글 입니다."));
//        CodyComment codyComment = new CodyComment();
//        codyComment.setCommentContent(commentRequestDto.getCommentContent());
//        codyComment.setUser(userDetails.getUser());
//        codyComment.setCommentUsername(userDetails.getUsername());
//        codyComment.setCody(cody);
//
//        commentRepository.save(codyComment);
//    }


    ////////////////////////////////////시큐리티 없이 테스트용///////////////////////////////////////////////////////////
    public void createComment(CommentRequestDto commentRequestDto, User user){
        Cody cody = codyRepository.findById(commentRequestDto.getCodyId()).orElseThrow(
                ()->new NullPointerException("존재하지 않는 게시글 입니다."));
        CodyComment codyComment = new CodyComment();
        codyComment.setCommentContent(commentRequestDto.getCommentContent());
        codyComment.setUser(user);
        codyComment.setCommentUsername(user.getUsername());
        codyComment.setCody(cody);

        commentRepository.save(codyComment);
    }
    ////////////////////////////////////시큐리티 없이 테스트용///////////////////////////////////////////////////////////









    //댓글 수정
    public void updateComment(Long commentId,
                              CommentRequestDto commentRequestDto,
                              UserDetailsImpl userDetails){
        Comment comment= commentRepository.findById(commentId).orElseThrow(
                ()-> new NullPointerException("코멘트를 찾을 수 없습니다."));

        comment.setCommentContent(commentRequestDto.getCommentContent());
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
>>>>>>> 84b6353e75f8e26db9ab1a16ecf6572eddd4bfd1
}
