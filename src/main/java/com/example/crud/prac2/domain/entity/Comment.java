package com.example.crud.prac2.domain.entity;

import com.example.crud.prac2.domain.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    // update 메서드
    public void updateCommentInfo(CommentRequestDto commentRequestDto) {
        if (commentRequestDto.getContent() != null) {
            this.content = commentRequestDto.getContent();
        }
    }
}
