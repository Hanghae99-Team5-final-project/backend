package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.Cody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodyRepository extends JpaRepository<Cody, Long> {
    List<Cody> findAllByOrderByCreatedAtDesc();
    List<Cody> findAllByUserId(Long codyId);
    List<Cody> findTop5ByOrderByIdDesc();
}