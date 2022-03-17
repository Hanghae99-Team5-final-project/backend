package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.Likes;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes,Long> {

    Optional<Likes> findById(Long likesId);
    List<Likes> findAllByUserId(Long userId);



}