package com.sparta.team5finalproject.service;

import com.sparta.team5finalproject.dto.watchDto.WatchDetailLikeResponseDto;
import com.sparta.team5finalproject.dto.watchDto.WatchDetailResponseDto;
import com.sparta.team5finalproject.dto.commentDto.CommentResponseDto;
import com.sparta.team5finalproject.model.*;
import com.sparta.team5finalproject.repository.CommentRepository;
import com.sparta.team5finalproject.repository.LikesRepository;
import com.sparta.team5finalproject.repository.WatchRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WatchService {

    private final WatchRepository watchRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;

    // 모바일 쿠팡 크롤링
    private static String cpWatchUrl = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=1";


    public void cpWatchCrawling() throws IOException {
        String url = cpWatchUrl;
        insertDatabase(url);
    }


    public void insertDatabase (String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements classLoading = doc.getElementsByClass("loading");

        // 썸네일 가져오기
        ArrayList<String> watchImage = new ArrayList<>();
        for (Element imgSrc : classLoading) {
            watchImage.add(imgSrc.attr("abs:src"));
        }

        // 제목 가져오기
        Elements classTitle = doc.getElementsByClass("title");
        ArrayList<String> watchTitle = new ArrayList<>();
        for (Element title : classTitle) {
            watchTitle.add(title.text());
        }

        // 가격 가져오기기
       Elements classDiscountPrice = doc.getElementsByClass("discount-price");
        ArrayList<String> watchPrice = new ArrayList<>();
        for (Element discountPrice : classDiscountPrice) {
            watchPrice.add(discountPrice.text());
        }

        // 워치 객체에 저장 하기 위한 로직
        for (int i=0; i<watchImage.size(); i++){
            Watch watch =new Watch(watchImage.get(i),watchTitle.get(i),watchPrice.get(i), WatchCategory.DIGITAL);
            watch.setLikeCount(0L);
            try{
                watchRepository.save(watch);
            } catch(IllegalArgumentException e){
                throw new IllegalArgumentException("시계 DB에 create 실패");
            }
        }

    }


    // 시계상세 페이지 조회 /
    public WatchDetailResponseDto readDetailWatch(Long watchId) {

        Watch watch = watchRepository.findById(watchId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 시계입니다."));
        List<WatchComment> watchCommentList = commentRepository.findAllByWatchOrderByCreatedAtAsc(watch);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment oneComment : watchCommentList) {
            commentResponseDtoList.add(CommentResponseDto.builder()
                    .commentId(oneComment.getId())
                    .commentUser(oneComment.getUser().getUsername())
                    .commentContent(oneComment.getCommentContent())
                    .createdAt(oneComment.getCreatedAt())
                    .build());
        }
        WatchDetailResponseDto watchDetailResponseDto = new WatchDetailResponseDto();
        watchDetailResponseDto.setWatchId(watch.getWatchId());
        watchDetailResponseDto.setWatchImage(watch.getWatchImageUrl());
        watchDetailResponseDto.setWatchBrand(watch.getWatchBrand());
        watchDetailResponseDto.setLowestPrice(watch.getLowestPrice());
        watchDetailResponseDto.setLikeCount(String.valueOf(watch.getLikeCount()));
        watchDetailResponseDto.setCommentResponseDtoList(commentResponseDtoList);

        return watchDetailResponseDto;

    }

    // 시계상세 페이지 라이크 조회
    public WatchDetailLikeResponseDto readDetailWatchLike(Long watchId, UserDetailsImpl userDetails) {
        WatchDetailLikeResponseDto watchDetailLikeResponseDto = new WatchDetailLikeResponseDto();
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 시계입니다."));
        if(userDetails != null){
            Optional<Likes> optLikes = likesRepository.findByWatchAndUser(watch, userDetails.getUser());
            if(optLikes.isPresent()){
                watchDetailLikeResponseDto.setExistLikes(true);
                Long likeId = optLikes.get().getId();
                watchDetailLikeResponseDto.setLikeId(likeId);
            } else {
                watchDetailLikeResponseDto.setExistLikes(false);
            }
        }
        return watchDetailLikeResponseDto;
    }

}
