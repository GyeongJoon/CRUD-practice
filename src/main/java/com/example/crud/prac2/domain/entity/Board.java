package com.example.crud.prac2.domain.entity;

import com.example.crud.prac2.domain.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // update 메소드
    public void updateBoardInfo(BoardRequestDto boardRequestDto) {
        if (boardRequestDto.getTitle() != null) {
            this.title = boardRequestDto.getTitle();
        }
        if (boardRequestDto.getContent() != null) {
            this.content = boardRequestDto.getContent();
        }
    }
}
