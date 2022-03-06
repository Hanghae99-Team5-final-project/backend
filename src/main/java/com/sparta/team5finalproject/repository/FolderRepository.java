//package com.sparta.team5finalproject.repository;
//
//import com.sparta.team5finalproject.model.Comment;
//import com.sparta.team5finalproject.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface FolderRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findAllByUser(User user);
//    List<Comment> findAllByUserAndNameIn(User user, List<String> names);
//}