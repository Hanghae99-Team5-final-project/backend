package com.sparta.team5finalproject.repository;


import com.sparta.team5finalproject.model.Cody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodyRepository extends JpaRepository<Cody, Long> {
//    Page<Cody> findAllByUserId(Long userId, Pageable pageable);
    Page<Cody> findAllByOrderByCreatedAtDesc(Pageable pageable);

    List<Cody> findAllByOrderByIdDesc();
//    Page<Cody> findAllByUserIdAndFolderList_Id(Long userId, Long folderId, Pageable pageable);
//    Page<Cody> findByUserId(Long userId);
    


}