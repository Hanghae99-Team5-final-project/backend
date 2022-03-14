package com.sparta.team5finalproject.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 값을 주입하여 객체를 만들기 위함
@Accessors(chain = true)
@Getter // 값을 가져오기 위함
@Entity
public class BestWatch {
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
    private String category; // 커플

    public BestWatch(String s, String s1, String s2) {
        this.watchImageUrl = s;
        this.watchBrand = s1;
        this.lowestPrice = s2;
        this.category = "best";
    }
}
