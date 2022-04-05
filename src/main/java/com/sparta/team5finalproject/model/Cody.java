package com.sparta.team5finalproject.model;

import com.sparta.team5finalproject.dto.codyDto.CodyRequestDto;
import com.sparta.team5finalproject.util.Timestamped;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cody extends Timestamped {
    // 코디 고유 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 코디 글 올린 유저 아이디
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 코디 글 제목
    @Column(nullable = false)
    private String codyTitle;

    // 코디 글 시계 브랜드
    @Column(nullable = false)
    private String watchBrand;

    // 코디 글 시계 모델
    @Column(nullable = false)
    private String watchModel;

    // 코디 글 코디 내용
    @Column(nullable = false)
    private String codyContent;

    // 코디 글 시계 사진들
    @Column(nullable = true)
    private String imageUrl;

    // 코디 글 시계 평점
    @Column(nullable = true)
    private String star;

    // 게시글 삭제 => 해당 게시글 댓글 모두 삭제
    @OneToMany(mappedBy = "cody", cascade = CascadeType.ALL)
    // 해당 코디 게시글 댓글 리스트
    private final List<CodyComment> codyCommentList = new ArrayList<>();


    public void update(CodyRequestDto codyRequestDto) {
        this.codyTitle = codyRequestDto.getCodyTitle();
        this.watchBrand = codyRequestDto.getWatchBrand();
        this.watchModel = codyRequestDto.getWatchModel();
        this.codyContent = codyRequestDto.getCodyContent();
        this.star = codyRequestDto.getStar();
    }

}
