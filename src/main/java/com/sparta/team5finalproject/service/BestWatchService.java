package com.sparta.team5finalproject.service;

import com.sparta.crawl.model.BestWatch;
import com.sparta.crawl.repository.BestWatchRepository;
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
public class BestWatchService {

    private final BestWatchRepository bestWatchRepository;

    //스누브 베스트 시계
    private static String BEST_WATCH = "http://www.snuv.co.kr/m/product_list.html?xcode=057&type=N&mcode=003&viewtype=gallery";

    @PostConstruct
    public void getBestWatch() throws IOException {
        Document doc = Jsoup.connect(BEST_WATCH).get();
//        System.out.println(doc);

        //사진
        ArrayList<String> watchImageUrl = new ArrayList<>();
        Elements imageUrlElements = doc.getElementsByClass("MS_prod_mobile_image listPic");
        for (Element element : imageUrlElements) {
            System.out.println(element.attr("abs:src"));
            watchImageUrl.add(element.attr("abs:src"));
        }

        //제목
        ArrayList<String> watchBrand = new ArrayList<>();
        Elements titleElements = doc.getElementsByClass("listName");
//        System.out.println(titleElements);
        for (int i = 0; i < titleElements.size(); i++) {
            final String title = titleElements.get(i).text();
            System.out.println("시계 제목 :" + title);
            watchBrand.add(title);
        }

        //가격
        ArrayList<String> lowestPrice = new ArrayList<>();
        Elements priceElements = doc.getElementsByClass("listPrice");
        for (int i = 0; i < priceElements.size(); i++) {
            final String price = priceElements.get(i).text();
            final String salePrice = getSalePrice(price);
            System.out.println(salePrice);
            lowestPrice.add(salePrice);
        }


        for (int i = 0; i < watchImageUrl.size(); i++) {
            BestWatch bestWatch = new BestWatch(watchImageUrl.get(i), watchBrand.get(i), lowestPrice.get(i));
            System.out.println(bestWatch);
            bestWatchRepository.save(bestWatch);
        }
    }

    private static String getSalePrice(final String price) {
        final String[] pricesArray = price.split(" ");
        return (pricesArray.length == 1) ? price : pricesArray[1];
    }

}
