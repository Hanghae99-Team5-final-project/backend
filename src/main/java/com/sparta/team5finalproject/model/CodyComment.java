package com.sparta.team5finalproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter @Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("CODY") // 엔티티 저장 시 구분 컬럼에 입력할 값을 지정
public class CodyComment extends Comment{

    // 코디 글의 댓글 쓴 코디 고유 아이디 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cody_id", nullable = true)
    private Cody cody;

}


