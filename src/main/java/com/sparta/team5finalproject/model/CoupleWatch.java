//package com.sparta.team5finalproject.model;
//
//import lombok.*;
//import lombok.experimental.Accessors;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder // 값을 주입하여 객체를 만들기 위함
//@Accessors(chain = true)
//@Getter // 값을 가져오기 위함
//@Entity
//public class CoupleWatch {
//
//    // 크롤링 전용 프로젝트를 하나를 판다. (db) - 작업하는 쇼핑몰 프로젝트에서 사용되는 (db)
//    // 1. [크롤링 -> db] (하루 기준으로)]
//    // 2. 작업하는 쇼핑몰 프로젝트 -> client -> response
//    // note) 크롤링 -> response 잦된다. (디도스로 파악될 수 있다)
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long watchId;
//
//    @Column(nullable = false)
//    private String watchImageUrl; // 이미지
//
//    @Column(nullable = false)
//    private String watchBrand; // 브랜드
//
//    @Column(nullable = false)
//    private String lowestPrice; // 가격
//
//    @Column(nullable = true)
//    private Long likeCount; //좋아요 수
//
//
//    @Column
//    @Enumerated(value = EnumType.STRING)
//    private WatchCategory watchCategory;
//
//    @OneToMany(mappedBy = "coupleWatch", cascade = CascadeType.ALL)    // 게시글 삭제 => 해당 게시글 댓글 모두 삭제
//    private List<WatchComment> watchCommentLists = new ArrayList<>();
//
//    @OneToMany(mappedBy = "coupleWatch", cascade = CascadeType.ALL)    // 게시글 삭제 => 해당 좋아요 모두 삭제
//    private List<Likes> likesList = new ArrayList<>();
//
//    public CoupleWatch(String s, String s1, String s2, WatchCategory watchCategory ) {
//        this.watchImageUrl = s;
//        this.watchBrand = s1;
//        this.lowestPrice = s2;
//        this.watchCategory = watchCategory;
//    }
//}
