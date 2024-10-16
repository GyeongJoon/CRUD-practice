package com.example.crud.prac2.domain.dto;

import com.example.crud.prac2.domain.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {

    private String memberName;

    private String email;

    private String password;

    private Department department;
}
