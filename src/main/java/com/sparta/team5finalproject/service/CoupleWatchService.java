package com.sparta.team5finalproject.service;

import com.sparta.crawl.model.CoupleWatch;
import com.sparta.crawl.repository.CoupleWatchRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CoupleWatchService {

    private final CoupleWatchRepository smartWatchRepository;
    //무신사
    private static String SMART_WATCH_DATAS_URL = "https://www.musinsa.com/search/musinsa/goods?q=%EC%BB%A4%ED%94%8C%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&list_kind=small&sortCode=pop&sub_sort=&page=1&display_cnt=0&saleGoods=&includeSoldOut=&setupGoods=&popular=&category1DepthCode=&category2DepthCodes=&category3DepthCodes=&selectedFilters=&category1DepthName=&category2DepthName=&brandIds=&price=&colorCodes=&contentType=&styleTypes=&includeKeywords=&excludeKeywords=&originalYn=N&tags=&campaignId=&serviceType=&eventType=&type=popular&season=&measure=&openFilterLayout=N&selectedOrderMeasure=&shoeSizeOption=&groupSale=&d_cat_cd=&attribute=";

    @PostConstruct
    public void getCoupleWatchDatas() throws IOException {
        Document doc = Jsoup.connect(SMART_WATCH_DATAS_URL).get();

        //썸네일
        ArrayList<String> watchImage = new ArrayList<>(); // 썸네일 담을 그릇용
        Elements e1 = doc.getElementsByClass("lazyload lazy");
        for (Element thumnail : e1){
//            System.out.println(thumnail.attr("abs:data-original"));
            watchImage.add(thumnail.attr("abs:data-original"));
        }


        ArrayList<String> watchBrand = new ArrayList<>();
        //제목
        for (Element title : e1){
//            System.out.println("제목 : "+ title.attr("alt"));
            watchBrand.add(title.attr("alt"));
        }


        ArrayList<String> lowestPrice = new ArrayList<>();
        //가격
        Elements e2 = doc.getElementsByClass("price");
        for (int i = 1; i < e2.size(); i++) {
            String price = e2.get(i).text();
            String realPrice = getRealPrice(price);

            String intStr = realPrice.replaceAll("[^0-9]", "");
//            System.out.println("가격 : " + intStr);
            lowestPrice.add(intStr);
        }

        //스마트워치 객체에 저장 하기 위한 로직
       for (int i=0; i<watchImage.size(); i++){
            CoupleWatch smartWatch = new CoupleWatch(watchImage.get(i),watchBrand.get(i),lowestPrice.get(i));
            smartWatchRepository.save(smartWatch);
        }
    }



    private static String getRealPrice(final String price) {
        final String[] pricesArray = price.split(" ");
        return (pricesArray.length == 1) ? price : pricesArray[1];
    }


}