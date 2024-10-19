package com.example.crud.prac2.domain.mapper;

import com.example.crud.prac2.domain.dto.BoardRequestDto;
import com.example.crud.prac2.domain.dto.BoardResponseDto;
import com.example.crud.prac2.domain.entity.Board;
import com.example.crud.prac2.domain.entity.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BoardMapper {
    // Dto -> Entity
    public static Board toBoardEntity(Member member, BoardRequestDto boardRequestDto) {
        return Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .member(member)
                .build();
    }

    // Entity -> Dto (전체)
    public static List<BoardResponseDto> toBoardAllDto(List<Board> boards) {
        return boards.stream()
                .map(board -> BoardResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .authorName(board.getMember().getMemberName())
                        .createAt(board.getCreatedAt())
                        .updateAt(board.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // Entity -> Dto
    public static BoardResponseDto toBoardDto(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .authorName(board.getMember().getMemberName())
                .createAt(board.getCreatedAt())
                .updateAt(LocalDateTime.now())
                .build();
    }

}
