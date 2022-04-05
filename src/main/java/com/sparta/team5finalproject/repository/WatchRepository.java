package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.model.WatchCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchRepository extends JpaRepository<Watch,Long> {
    // 모든 시계 상품을 커플과 아날로그로 나누기
    List<Watch> findAllByWatchCategory(WatchCategory watchCategory);
    // 메인 페이지 인기상품 및 커플상품 TOP4 내림차순으로 가져오기
    List<Watch> findTop8ByWatchCategoryOrderByLikeCountDesc(WatchCategory category);

}
