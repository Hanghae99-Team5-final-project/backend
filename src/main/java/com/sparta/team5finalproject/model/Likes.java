package com.sparta.team5finalproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Likes {
    // 찜 고유 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    // 찜한 유저 고유 아이디 정보
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    // 찜한 시계 고유 아이디 정보
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "watchId")
    private Watch watch;

    public Likes(User user, Watch watch) {
        this.user = user;
        this.watch = watch;
    }
}
