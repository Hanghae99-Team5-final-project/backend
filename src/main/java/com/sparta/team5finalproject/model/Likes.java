package com.sparta.team5finalproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Likes {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "like_id")
    private Long id;

    //watch에 likesCount 칼럼이 있어야 할 듯.
    @ManyToOne
    @JoinColumn(name = "watch_id")
    private Watch watch;

    @ManyToOne
    @JoinColumn(name = "uesr_id")
    private User user;
}
