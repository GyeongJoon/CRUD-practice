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
public class BoardResponseDto {

    private Long id;

    private String title;

    private String content;

    private String authorName;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
