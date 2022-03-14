package com.sparta.team5finalproject.repository;

import com.sparta.crawl.model.CoupleWatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoupleWatchRepository extends JpaRepository<CoupleWatch,Long> {

    List<CoupleWatch> findAllByOrderByLikeCountDesc();
    List<CoupleWatch> findAll(String category);
}
