package com.sparta.team5finalproject.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

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
    private String category; // 남성 시계

    public Watch(String s, String s1, String s2) {
        this.watchImageUrl = s;
        this.watchBrand = s1;
        this.lowestPrice = s2;
        this.category = "남성시계";
    }
}
