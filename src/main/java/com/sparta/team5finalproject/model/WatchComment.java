package com.sparta.team5finalproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("WATCH")
public class WatchComment extends Comment{
    // 시계 댓글 쓴 시계 고유 아이디 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "watch_id", nullable = true)
    private Watch watch;

}
