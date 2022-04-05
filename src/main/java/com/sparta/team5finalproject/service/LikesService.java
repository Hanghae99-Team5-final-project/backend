package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.exception.UnmatchUserException;
import com.sparta.team5finalproject.model.Likes;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.repository.LikesRepository;
import com.sparta.team5finalproject.repository.WatchRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private Logger logger = LoggerFactory.getLogger(LikesService.class);

    private final LikesRepository likesRepository;
    private final WatchRepository watchRepository;

    //찜 생성
    @Transactional
    public String createLikes(Long watchId, UserDetailsImpl userDetails) {
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                () -> new NullPointerException("해당 상품이 존재하지 않습니다."));
        User user = userDetails.getUser();
        // 로그인한 유저가 해당 시계 상품에 좋아요를 했는지 여부
        Optional<Likes> optLikes = likesRepository.findByWatchAndUser(watch, userDetails.getUser());
        if (optLikes.isPresent()) {
            Likes likes = optLikes.get();
            if (user == null) {
                throw new NullPointerException("로그인이 필요합니다.");
            } else if (user.getId().equals(likes.getUser().getId())) {
                try {
                    likesRepository.delete(likes);
                } catch (IllegalArgumentException e) {
                    // 예외처리, 로깅
                    String detailMessage = String.format("찜 DB에 delete 실패, Input: %s", likes.getId());
                    logger.info(detailMessage);
                    throw new IllegalArgumentException(detailMessage);
                }
                // watch 테이블의 좋아요 갯수 설정
                Long likeCnt = watch.getLikeCount();
                likeCnt--;
                watch.setLikeCount(likeCnt);
                try {
                    watchRepository.save(watch);
                } catch (IllegalArgumentException e) {
                    // 예외처리, 로깅
                    String detailMessage = String.format("시계 DB에 likeCnt 감소 실패, Input: %s", watch.getWatchId());
                    logger.info(detailMessage);
                    throw new IllegalArgumentException(detailMessage);
                }
            } else {
                // 예외처리, 로깅
                logger.info("해당 유저가 아닙니다.");
                throw new IllegalArgumentException("해당 유저가 아닙니다.");
            }
        } else {
            if (user == null) {
                logger.info("로그인이 필요합니다.");
                throw new NullPointerException("로그인이 필요합니다.");
            }
            Likes createLikes = new Likes(userDetails.getUser(), watch);
            try {
                likesRepository.save(createLikes);
            } catch (IllegalArgumentException e) {
                // 예외처리, 로깅
                String detailMessage = String.format("찜 DB에 create 실패, Input: %s", createLikes.getUser().getId());
                logger.info(detailMessage);
                throw new IllegalArgumentException(detailMessage);
            }

            // watch 테이블의 좋아요 갯수 설정
            Long likeCnt = watch.getLikeCount();
            likeCnt++;
            watch.setLikeCount(likeCnt);
            try {
                watchRepository.save(watch);
            } catch (IllegalArgumentException e) {
                // 예외처리, 로깅
                String detailMessage = String.format("watch DB에 likeCnt 증가 실패, Input: %s", watch.getWatchId());
                logger.info(detailMessage);
                throw new IllegalArgumentException(detailMessage);
            }
        }
        return "{\"result\":\"true\"}";
    }


    //찜 삭제 (이 메소드 필요없을듯)
    @Transactional
    public String deleteLikes(Long likesId, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Likes likes = likesRepository.findById(likesId).orElseThrow(
                () -> new NullPointerException("deleteLike 내부 findByLikesId 오류"));
        Watch watch = watchRepository.findById(likes.getWatch().getWatchId()).orElseThrow(
                () -> new NullPointerException("해당 상품이 존재하지 않습니다."));

        if (user == null) {
            new NullPointerException("로그인이 필요합니다.");
        } else if (user.getId().equals(likes.getUser().getId())) {
            deleteLikes(likes);
            // watch 테이블의 좋아요 갯수 설정
            Long likeCnt = watch.getLikeCount();
            likeCnt--;
            watch.setLikeCount(likeCnt);
            try {
                watchRepository.save(watch);
            } catch (IllegalArgumentException e) {
                // 예외처리, 로깅
                String detailMessage = String.format("watch DB에 likeCnt 감소 실패, Input: %s", watch.getWatchId());
                logger.info(detailMessage);
                throw new IllegalArgumentException(detailMessage);
            }
        } else {
            throw new UnmatchUserException("해당 유저가 아닙니다.");
        }
        return "{\"result\":\"true\"}";
    }

    // Cascade 테스트용 delete 메소드
    public void deleteLikes(Likes likes) {
        try {
            likesRepository.delete(likes);
        } catch (IllegalArgumentException e) {
            // 예외처리, 로깅
            String detailMessage = String.format("찜 DB에 delete 실패, Input: %s", likes.getId());
            logger.info(detailMessage);
            throw new IllegalArgumentException(detailMessage);
        }
    }
}



