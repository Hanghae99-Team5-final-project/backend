package com.sparta.team5finalproject.model;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long watchId;

    @Column(nullable = false)
    private String watchImageUrl; // 이미지

    @Column(nullable = false)
    private String watchBrand; // 브랜드

    @Column(nullable = false)
    private String lowestPrice; // 가격

    @Column(nullable = true)
    private Long likeCount; //좋아요 수

    @Column
    @Enumerated(value = EnumType.STRING)
    private WatchCategory watchCategory;

    @OneToMany(mappedBy = "watch", cascade = CascadeType.ALL)    // 게시글 삭제 => 해당 게시글 댓글 모두 삭제
    private List<WatchComment> watchCommentLists = new ArrayList<>();

    @OneToMany(mappedBy = "watch", cascade = CascadeType.ALL)    // 게시글 삭제 => 해당 좋아요 모두 삭제
    private List<Likes> likesList = new ArrayList<>();

    public Watch(String s, String s1, String s2, WatchCategory watchCategory) {
        this.watchImageUrl = s;
        this.watchBrand = s1;
        this.lowestPrice = s2;
        this.watchCategory = watchCategory;
    }
}
