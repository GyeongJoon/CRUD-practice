package com.example.crud.prac2.domain.mapper;

import com.example.crud.prac2.domain.dto.CommentRequestDto;
import com.example.crud.prac2.domain.dto.CommentResponseDto;
import com.example.crud.prac2.domain.entity.Board;
import com.example.crud.prac2.domain.entity.Comment;
import com.example.crud.prac2.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    // Dto -> Entity
    public static Comment toCommentEntity(Member member, Board board, CommentRequestDto commentRequestDto) {
        return Comment.builder()
                .content(commentRequestDto.getContent())
                .member(member)
                .board(board)
                .build();
    }

    // Entity -> Dto (전체)
    public static List<CommentResponseDto> toCommentAllDto(List<Comment> comments) {
        return comments.stream()
                .map(comment -> CommentResponseDto.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .authorName(comment.getMember().getMemberName())
                        .department(comment.getMember().getDepartment().getDescription())
                        .createAt(comment.getCreatedAt())
                        .updateAt(comment.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // Entity -> Dto
    public static CommentResponseDto toCommentDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .authorName(comment.getMember().getMemberName())
                .department(comment.getMember().getDepartment().getDescription())
                .createAt(comment.getCreatedAt())
                .updateAt(comment.getUpdatedAt())
                .build();
    }

    // Entity -> Dto (페이지)
    public static Page<CommentResponseDto> toCommentDtoPage(Page<Comment> commentPage) {
        return commentPage.map(CommentMapper::toCommentDto);
    }
}
