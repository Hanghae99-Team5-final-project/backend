package com.sparta.team5finalproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "watchId")
    private Watch watch;


    public Likes(User user, Watch watch) {
        this.user = user;
        this.watch = watch;

    }
}
