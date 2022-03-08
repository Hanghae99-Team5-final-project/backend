package com.sparta.team5finalproject.model;

import com.sparta.team5finalproject.dto.CodyRequestDto;
import com.sparta.team5finalproject.util.Timestamped;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cody extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String star;


}
