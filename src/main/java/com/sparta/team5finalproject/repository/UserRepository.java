package com.sparta.team5finalproject.repository;

import com.sparta.team5finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 유저 아이디 불러오기
    Optional<User> findByUsername(String username);
    // 카카오 고유 ID 불러오기
    Optional<User> findByKakaoId(Long kakaoId);

}