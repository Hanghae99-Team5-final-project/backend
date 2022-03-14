package com.sparta.team5finalproject.service;

import com.sparta.team5finalproject.dto.commentDto.CommentRequestDto;
import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.CodyComment;
import com.sparta.team5finalproject.model.Comment;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.CommentRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CodyRepository codyRepository;

    //댓글 생성
    public void createComment(CommentRequestDto commentRequestDto, UserDetailsImpl userDetails){
        Cody cody = codyRepository.findById(commentRequestDto.getCodyId()).orElseThrow(
                ()->new NullPointerException("존재하지 않는 게시글 입니다."));
        CodyComment codyComment = new CodyComment();
        codyComment.setCommentContent(commentRequestDto.getCommentContent());
        codyComment.setUser(userDetails.getUser());
        codyComment.setCommentUsername(userDetails.getUsername());
        codyComment.setCody(cody);

        commentRepository.save(codyComment);
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
}
