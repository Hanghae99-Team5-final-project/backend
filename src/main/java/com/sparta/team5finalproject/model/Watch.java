package com.sparta.team5finalproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Getter
@Entity
public class Watch {
    //시계 고유 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long watchId;

    // 시계 이미지 주소
    @Column(nullable = false)
    private String watchImageUrl;

    // 시계 제목
    @Column(nullable = false)
    private String watchBrand;

    // 시계 가격
    @Column(nullable = false)
    private String lowestPrice;

    // 시계 좋아요 갯수
    @Column(nullable = true)
    private Long likeCount;

    // 시계 카테고리
    @Column
    @Enumerated(value = EnumType.STRING)
    private WatchCategory watchCategory;

    // 해당 시계 댓글 리스트
    @JsonBackReference
    @OneToMany(mappedBy = "watch", cascade = CascadeType.ALL)
    private List<WatchComment> watchCommentLists = new ArrayList<>();

    // 해당 시계 찜한 리스트
    @OneToMany(mappedBy = "watch")
    @JsonBackReference
    private List<Likes> likesList = new ArrayList<>();


    public Watch(String s, String s1, String s2, WatchCategory watchCategory) {
        this.watchImageUrl = s;
        this.watchBrand = s1;
        this.lowestPrice = s2;
        this.watchCategory = watchCategory;
    }
}
