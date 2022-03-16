package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.model.Likes;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.repository.LikesRepository;
import com.sparta.team5finalproject.repository.UserRepository;
import com.sparta.team5finalproject.repository.WatchRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {


    private final LikesRepository likesRepository;
    //쿠팡
    private final WatchRepository watchRepository;

    //찜 생성
    @Transactional
    public String createLikes(Long watchId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                () -> new NullPointerException("해당 상품이 존재하지 않습니다."));
        Likes likes = new Likes(userDetails.getUser(), watch);
        likesRepository.save(likes);
        Long likeCnt = watch.getLikeCount();
        likeCnt++;
        watch.setLikeCount(likeCnt);
        watchRepository.save(watch);
        return "{\"result\":\"true\"}";
    }

    //찜 삭제
    @Transactional
    public String deleteLikes(Long likesId) {
        System.out.println("에러냐? "+likesId);
        Likes likes = likesRepository.findById(likesId).orElseThrow(
                ()->new NullPointerException("deleteLike 내부 findByLikesId 오류"));
        Watch watch = watchRepository.findById(likes.getWatch().getWatchId()).orElseThrow(
                () -> new NullPointerException("해당 상품이 존재하지 않습니다."));

        System.out.println("에러냐2? "+likesId);
        likesRepository.delete(likes);
        Long likeCnt = watch.getLikeCount();
        likeCnt--;
        watch.setLikeCount(likeCnt);
        watchRepository.save(watch);
        return "{\"result\":\"false\"}";
    }


}

