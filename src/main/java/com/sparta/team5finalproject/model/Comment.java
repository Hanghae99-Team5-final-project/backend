package com.sparta.team5finalproject.model;

import com.sparta.team5finalproject.dto.commentDto.CommentRequestDto;
import com.sparta.team5finalproject.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter @Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CommentCategory")
public abstract class Comment extends Timestamped {

    // 댓글 고유 아이디
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 댓글 쓴 유저 ID
    @Column(nullable = false)
    private String commentUsername;

    // 댓글 내용
    @Column(nullable = false)
    private String commentContent;

    // 댓글 쓴 유저 고유 아이디 정보
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public void update(CommentRequestDto commentRequestDto){
        this.commentContent = commentRequestDto.getCommentContent();
    }

}