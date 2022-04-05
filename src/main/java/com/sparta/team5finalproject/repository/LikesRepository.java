package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.Likes;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    // 해당 시계의 내가 누른 찜 삭제
    Optional<Likes> findById(Long likesId);
    // 좋아요 누른 유저 정보들 불러오기
    List<Likes> findAllByUserId(Long userId);
    // 로그인한 유저의 좋아요 누른 시계 상품 불러오기
    Optional<Likes> findByWatchAndUser(Watch watch, User user);

}