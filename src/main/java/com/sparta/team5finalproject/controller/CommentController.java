package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.repository.CommentRepository;
import com.sparta.team5finalproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @Autowired
    public CommentController(
            CommentRepository commentRepository,
            CommentService commentService) {
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    @PostMapping("/comment/write/{codyId}")

    @PostMapping("/comment/write/{watchId}")
}
