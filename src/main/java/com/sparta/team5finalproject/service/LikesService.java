package com.sparta.team5finalproject.service;

import com.sparta.crawl.model.Likes;
import com.sparta.crawl.repository.LikesRepository;
import com.sparta.team5finalproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    //쿠팡
    private final WatchRepository watchRepository;

    //찜 생성
    public String createLikes(Long watchId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                ()-> new NullPointerException("해당 상품이 존재하지 않습니다."));
        Likes likes = new Likes(userDetails.getId(), watch);
        likesRepository.save(likes);
        return  "{\"result\":\"true\"}";
    }

    //찜 삭제
    public String deleteLikes(Long watchId){
        Watch watch = watchRepository.findById(watchId).orElsThrow(
                ()-> new NullPointerException(("해당 상품이 존재하지 않습니다.")));
        likesRepository.delete(watch);
        return "{\"result\":\"false\"}";
    }

    //시계 상세 페이지


}
