package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.dto.CommentRequestDto;
import com.sparta.team5finalproject.dto.CommentResopnseDto;
import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.CodyComment;
import com.sparta.team5finalproject.model.Comment;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.CommentRepository;
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

}
