package com.sparta.team5finalproject.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@Builder
@NoArgsConstructor
public class  Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String watchImage;

    @Column(nullable = false)
    private String watchBrand;

    @Column(nullable = false)
    private String watchModel;

    @Column(nullable = false)
    private Long lowestPrice;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String category;

}
