package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 코디 글의 작성된 댓글 시간으로 오름차순 정렬
    List<CodyComment> findAllByCodyOrderByCreatedAtAsc(Cody cody);
    // 시계의 작성된 댓글 시간으로 오름차순 정렬
    List<WatchComment> findAllByWatchOrderByCreatedAtAsc(Watch watch);
}