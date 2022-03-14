package com.sparta.team5finalproject.service;

import com.sparta.crawl.dto.MainPageResponseDto;
import com.sparta.crawl.dto.WatchDetailResponseDto;
import com.sparta.crawl.model.BestWatch;
import com.sparta.crawl.model.CoupleWatch;
import com.sparta.crawl.model.WatchCategory;
import com.sparta.crawl.repository.BestWatchRepository;
import com.sparta.crawl.repository.CoupleWatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainPageService {
    private final BestWatchRepository bestWatchRepository;
    private final CoupleWatchRepository coupleWatchRepository;
    private final CodyRepository codyRepository;
    private final WatchRepository watchRepository;

    // 메인페이지 조회
    public MainPageResponseDto getMainPageWatchList() {

        // 인기 상품
        MainPageResponseDto mainPageResponseDto = new MainPageResponseDto();
        List<BestWatch> tempBestWatchList = bestWatchRepository.findAllByOrderByLikeCountDesc();
        List<BestWatch> bestWatchList = new ArrayList<>();
        for (int i=0; i<5; i++) {
            bestWatchList.add(tempBestWatchList.get(i));
        }
        mainPageResponseDto.setBestList(bestWatchList);

        // 커플 상품
        List<CoupleWatch> tempCoupleWatchList = coupleWatchRepository.findAllByOrderByLikeCountDesc();
        List<CoupleWatch> coupleWatchList = new ArrayList<>();
        for (int j=0; j<5; j++) {
            coupleWatchList.add(tempCoupleWatchList.get(j));
        }
        mainPageResponseDto.setCoupleList(coupleWatchList);

        // 코디글
        List<Cody> tempCodyPostList = codyRepository.findAllByOderByCodyIdDesc();
        List<Cody> codyPostList = new ArrayList<>();
        for (int l=0; l<5; l++) {
            codyPostList.add(tempCodyPostList.get(l));
        }
        mainPageResponseDto.setCodyList(codyPostList);

        return mainPageResponseDto;
    }


        //카테고리별 페이지 조회
    public MainPageResponseDto getCategoryWatch() {
        MainPageResponseDto mainPageResponseDto = new MainPageResponseDto(); //그릇
        //bestWatch는 성원형님의 쿠팡 워치로 바꿔야 됨.
        List<BestWatch> digitalCategoryWatch = bestWatchRepository.findAll(WatchCategory.DIGITAL.getCategory());
        List<CoupleWatch> coupleCategoryWatch = coupleWatchRepository.findAll(WatchCategory.COUPLE.getCategory());
        mainPageResponseDto.setBestList(digitalCategoryWatch);
        mainPageResponseDto.setCoupleList(coupleCategoryWatch);
        return mainPageResponseDto;
    }


    // 시계상세 페이지 조회
    public WatchDetailResponseDto readDeatailWatch(Long watchId) {
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 시계입니다."));

        List<Comment> watchCommentList = commentRepository.findAllByCodyOrderByCreatedAtAsc(watch);
        List<CommentResopnseDto> commentResopnseDtoList = new ArrayList<>();
        for(Comment oneComment : watchCommentList) {
            commentResopnseDtoList.add(CommentResopnseDto.builder()
                    .commentId(oneComment.getId())
                    .commentUser(user.getUsername())
                    .commentContent(oneComment.getCommentContent())
                    .createdAt(oneComment.getCreatedAt())
                    .build());
        }
        WatchDetailResponseDto watchDetailResponseDto = new WatchDetailResponseDto();
        watchDetailResponseDto.setWatchId(watch.getWatchId);
        watchDetailResponseDto.setWatchImage(watch.getWatchImage);
        watchDetailResponseDto.setWatchBrand(watch.getWatchBrand);
        watchDetailResponseDto.setLowestPrice(watch.getLowestPrice);
        watchDetailResponseDto.setLikeCount(watch.getLikeCount);
        watchDetailResponseDto.setCommentResponseDtoList(commentResopnseDtoList);
        return watchDetailResponseDto;




    }


}
