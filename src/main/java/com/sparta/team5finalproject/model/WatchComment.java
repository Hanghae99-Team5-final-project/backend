package com.sparta.team5finalproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
//@DiscriminatorValue : 엔티티 저장 시 구분 컬럼에 입력할 값을 지정
@DiscriminatorValue("WATCH")
public class WatchComment extends Comment{

    @ManyToOne
    @JoinColumn(name = "watch_id", nullable = true) // name = watch_id
    private Watch watch;

}
