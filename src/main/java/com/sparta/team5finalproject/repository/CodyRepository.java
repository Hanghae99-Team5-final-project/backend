package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.Cody;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodyRepository extends JpaRepository<Cody, Long> {
    // 코디 글 생성 시간으로 내림차순 정렬
    List<Cody> findAllByOrderByCreatedAtDesc();

    // 내가 올린 코디 글 정렬
    List<Cody> findAllByUserId(Long codyId);

    // 해당 코디 게시글 TOP5 순서대로 정렬
    List<Cody> findTop5ByOrderByIdDesc();
}