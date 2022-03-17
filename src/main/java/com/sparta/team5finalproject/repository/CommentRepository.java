package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CodyComment> findAllByCodyOrderByCreatedAtAsc(Cody cody);
    List<WatchComment> findAllByWatchOrderByCreatedAtAsc(Watch watch);
}