package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes,Long> {

    Optional<Likes> findById(Long likesId);
    List<Likes> findAllByWatch_WatchId(Long watchId);

}