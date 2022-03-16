//package com.sparta.team5finalproject.repository;
//
//
//import com.sparta.team5finalproject.model.CoupleWatch;
//import com.sparta.team5finalproject.model.Watch;
//import com.sparta.team5finalproject.model.WatchCategory;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface CoupleWatchRepository extends JpaRepository<CoupleWatch, Long> {
//    List<Watch> findAllByOrderByLikeCountDesc();
//    //    List<CoupleWatch> findAllByOrderByLikeCountDesc();
////    List<CoupleWatch> findAll(String category);
//    List<Watch> findAllByWatchCategory(WatchCategory couple);
//}
