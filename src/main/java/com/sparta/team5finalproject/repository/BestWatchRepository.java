package com.sparta.team5finalproject.repository;

import com.sparta.crawl.model.BestWatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestWatchRepository extends JpaRepository<BestWatch, Long> {
    List<BestWatch> findAllByOrderByLikeCountDesc();
    List<BestWatch> findAll(String category);
}
