package com.sparta.team5finalproject.model;

import com.sparta.team5finalproject.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CommentCategory")
public abstract class Comment extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String commentUser;

    @Column(nullable = false)
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


//    public Comment(String name, User user) {
//        this.name = name;
//        this.user = user;
//    }
}