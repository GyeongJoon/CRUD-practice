package com.example.crud.prac2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {

    private Long id;

    private String content;

    private String authorName;

    private String department;

    private LocalDateTime createAt;
    
    private LocalDateTime updateAt;
}
