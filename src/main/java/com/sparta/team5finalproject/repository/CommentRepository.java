package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.Comment;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.Watch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCodyOrderByCreatedAtAsc(Cody cody);
    List<Comment> findAllByUser(User findUser);


}