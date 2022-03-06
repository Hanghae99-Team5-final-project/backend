package com.sparta.team5finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;                            // 테이블 기본키

    @Column(columnDefinition = "TEXT")
    private String imageLink;                   // Amazon S3 링크

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODY_ID")
    private Cody cody;
}
