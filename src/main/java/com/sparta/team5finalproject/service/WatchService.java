package com.sparta.team5finalproject.service;

import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class WatchService {

    private final WatchRepository watchRepository;

    private static String cpWatchUrl1 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=1";
    private static String cpWatchUrl2 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=2";
    private static String cpWatchUrl3 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=3";
    private static String cpWatchUrl4 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=4";
    private static String cpWatchUrl5 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=5";
    private static String cpWatchUrl6 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=6";
    private static String cpWatchUrl7 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=7";
    private static String cpWatchUrl8 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=8";
    private static String cpWatchUrl9 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=9";
    private static String cpWatchUrl10 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=10";
    private static String cpWatchUrl11 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=11";
    private static String cpWatchUrl12 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=12";
    private static String cpWatchUrl13 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=13";
    private static String cpWatchUrl14 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=14";
    private static String cpWatchUrl15 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=15";
    private static String cpWatchUrl16 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=16";
    private static String cpWatchUrl17 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=17";
    private static String cpWatchUrl18 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=18";
    private static String cpWatchUrl19 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=19";
    private static String cpWatchUrl20 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=20";
    private static String cpWatchUrl21 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=21";
    private static String cpWatchUrl22 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=22";
    private static String cpWatchUrl23 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=23";
    private static String cpWatchUrl24 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=24";
    private static String cpWatchUrl25 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=25";
    private static String cpWatchUrl26 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=26";
    private static String cpWatchUrl27 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=27";
    private static String cpWatchUrl28 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=28";
    private static String cpWatchUrl29 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=29";
    private static String cpWatchUrl30 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=30";
    private static String cpWatchUrl31 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=31";
    private static String cpWatchUrl32 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=32";
    private static String cpWatchUrl33 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=33";
    private static String cpWatchUrl34 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=34";
    private static String cpWatchUrl35 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=35";
    private static String cpWatchUrl36 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=36";
    private static String cpWatchUrl37 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=37";
    private static String cpWatchUrl38 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=38";
    private static String cpWatchUrl39 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=39";
    private static String cpWatchUrl40 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=40";
    private static String cpWatchUrl41 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=41";
    private static String cpWatchUrl42 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=42";
    private static String cpWatchUrl43 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=43";
    private static String cpWatchUrl44 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=44";
    private static String cpWatchUrl45 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=45";
    private static String cpWatchUrl46 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=46";
    private static String cpWatchUrl47 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=47";
    private static String cpWatchUrl48 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=48";
    private static String cpWatchUrl49 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=49";
    private static String cpWatchUrl50 = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page=50";
//    private static String cpWatchUrl;

    public void total() throws IOException {
//        String[] abc;
//        for (int i=1; i<51; i++) {
//            cpWatchUrl = "https://m.coupang.com/nm/search?q=%EC%86%90%EB%AA%A9%EC%8B%9C%EA%B3%84&page="+i;
//            abc.
//        }
        String[] urlList = {cpWatchUrl1,cpWatchUrl2,cpWatchUrl3,cpWatchUrl4,cpWatchUrl5,cpWatchUrl6,cpWatchUrl7,cpWatchUrl8,cpWatchUrl9,cpWatchUrl10
                ,cpWatchUrl11,cpWatchUrl12,cpWatchUrl13,cpWatchUrl14,cpWatchUrl15,cpWatchUrl16,cpWatchUrl17,cpWatchUrl18,cpWatchUrl19,cpWatchUrl20
                ,cpWatchUrl21,cpWatchUrl22,cpWatchUrl23,cpWatchUrl24,cpWatchUrl25,cpWatchUrl26,cpWatchUrl27,cpWatchUrl28,cpWatchUrl29,cpWatchUrl30
                ,cpWatchUrl31,cpWatchUrl32,cpWatchUrl33,cpWatchUrl34,cpWatchUrl35,cpWatchUrl36,cpWatchUrl37,cpWatchUrl38,cpWatchUrl39,cpWatchUrl40
                ,cpWatchUrl41,cpWatchUrl42,cpWatchUrl43,cpWatchUrl44,cpWatchUrl45,cpWatchUrl46,cpWatchUrl47,cpWatchUrl48,cpWatchUrl49,cpWatchUrl50};
        for (String temp : urlList) {
            cpWatchData(temp);
            System.out.println(temp);
        }
    }

    public void cpWatchData (String z) throws IOException {
        Document doc = Jsoup.connect(z).get();
        Elements img = doc.getElementsByClass("loading");

        // 손목시계 이미지
        ArrayList<String> watchImage = new ArrayList<>();
        for (Element thumnail : img) {
            System.out.println(thumnail.attr("abs:src"));
            watchImage.add(thumnail.attr("abs:src"));
        }

        // 손목시계 제목
        Elements title = doc.getElementsByClass("title");
        ArrayList<String> watchTitle = new ArrayList<>();
        for (Element info : title) {
            System.out.println(info.text());
            watchTitle.add(info.text());
        }

        // 손목시계 가격
        Elements price = doc.getElementsByClass("discount-price");
        ArrayList<String> watchPrice = new ArrayList<>();
        for (Element temp : price) {
            System.out.println(temp.text());
            watchPrice.add(temp.text());
        }

        for (int i=0; i<watchImage.size(); i++){
            Watch watch =new Watch(watchImage.get(i),watchTitle.get(i),watchPrice.get(i));
            watchRepository.save(watch);
        }

    }

}
