package com.example.crud.prac2.domain.dto;

import com.example.crud.prac2.domain.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {

    private Long id;

    private String memberName;

    private String email;

    private Department department;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
