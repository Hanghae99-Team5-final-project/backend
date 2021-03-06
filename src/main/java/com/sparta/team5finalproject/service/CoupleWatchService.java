package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.model.WatchCategory;
import com.sparta.team5finalproject.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.ArrayList;


@RequiredArgsConstructor
@Service
public class CoupleWatchService {

    private Logger logger = LoggerFactory.getLogger(CoupleWatchService.class);

    private final WatchRepository watchRepository;
    //무신사 크롤링
    private static String SMART_WATCH_DATAS_URL = "https://www.musinsa.com/search/musinsa/goods?q=%EC%BB%A4%ED%94%8C%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&list_kind=small&sortCode=pop&sub_sort=&page=1&display_cnt=0&saleGoods=&includeSoldOut=&setupGoods=&popular=&category1DepthCode=&category2DepthCodes=&category3DepthCodes=&selectedFilters=&category1DepthName=&category2DepthName=&brandIds=&price=&colorCodes=&contentType=&styleTypes=&includeKeywords=&excludeKeywords=&originalYn=N&tags=&campaignId=&serviceType=&eventType=&type=popular&season=&measure=&openFilterLayout=N&selectedOrderMeasure=&shoeSizeOption=&groupSale=&d_cat_cd=&attribute=";

    public void msWatchCrawling() throws IOException {
        String urlList = SMART_WATCH_DATAS_URL;
        getCoupleWatchDatas(urlList);
    }


    @Transactional
    public void getCoupleWatchDatas(String urlList) throws IOException {
        Document doc = Jsoup.connect(urlList).get();
        Elements classLazy = doc.getElementsByClass("lazyload lazy");

        //썸네일 가져오기
        ArrayList<String> watchImage = new ArrayList<>();
        for (Element thumnail : classLazy) {
            watchImage.add(thumnail.attr("abs:data-original"));
        }

        //제목 가져오기기
        ArrayList<String> watchBrand = new ArrayList<>();
        for (Element title : classLazy) {
            watchBrand.add(title.attr("alt"));
        }

        //가격 가져오기기
        ArrayList<String> lowestPrice = new ArrayList<>();
        Elements classPrice = doc.getElementsByClass("price");
        for (int i = 1; i < classPrice.size(); i++) {
            String price = classPrice.get(i).text();
            String realPrice = removeSpace(price);
            String intStr = realPrice.replaceAll("[^0-9]", "");
            lowestPrice.add(intStr + "원");
        }

        //워치 객체에 저장 하기 위한 로직
        for (int i = 0; i < watchImage.size(); i++) {
            Watch watch = new Watch(watchImage.get(i), watchBrand.get(i), lowestPrice.get(i), WatchCategory.COUPLE);
            watch.setLikeCount(0L);
            try {
                watchRepository.save(watch);
            } catch (IllegalArgumentException e) {
                // 예외처리, 로깅
                String detailMessage = String.format("커플시계 DB에 create 실패, Input: %s", watch.getWatchId());
                logger.info(detailMessage);
                throw new IllegalArgumentException(detailMessage);
            }
        }
    }
    // 가격 가져올 때 띄어쓰기와 한글 삭제하는 함수
    private static String removeSpace(final String price) {
        final String[] pricesArray = price.split(" ");
        return (pricesArray.length == 1) ? price : pricesArray[1];
    }
}