package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.model.WatchCategory;
import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
import java.util.Optional;

public interface WatchRepository extends JpaRepository<Watch,Long> {

    List<Watch> findAllByWatchCategory(WatchCategory watchCategory);
    List<Watch> findTop4ByWatchCategoryOrderByLikeCountDesc(WatchCategory category);



}
