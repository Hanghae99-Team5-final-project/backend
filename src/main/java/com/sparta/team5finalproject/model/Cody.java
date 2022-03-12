package com.sparta.team5finalproject.model;

import com.sparta.team5finalproject.dto.CodyRequestDto;
import com.sparta.team5finalproject.util.Timestamped;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cody extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String codyTitle;

    @Column(nullable = false)
    private String watchBrand;

    @Column(nullable = false)
    private String watchModel;

    @Column(nullable = false)
    private String codyContent;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private String star;

    @OneToMany(mappedBy = "cody", cascade = CascadeType.ALL)    // 게시글 삭제 => 해당 게시글 댓글 모두 삭제
    private final List<CodyComment> codyCommentList = new ArrayList<>();



    //--------------------성원
    public void update(CodyRequestDto codyRequestDto) {
        this.codyTitle = codyRequestDto.getCodyTitle();
        this.watchBrand = codyRequestDto.getWatchBrand();
        this.watchModel = codyRequestDto.getWatchModel();
        this.codyContent = codyRequestDto.getCodyContent();
//        this.imageUrl = codyRequestDto.getImageUrl();
        this.star = codyRequestDto.getStar();
    }

}
