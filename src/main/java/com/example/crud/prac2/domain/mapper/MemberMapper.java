package com.example.crud.prac2.domain.mapper;

import com.example.crud.prac2.domain.dto.MemberRequestDto;
import com.example.crud.prac2.domain.dto.MemberResponseDto;
import com.example.crud.prac2.domain.entity.Member;

public class MemberMapper {
    // Dto -> Entity
    public static Member toMemberEntity(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .memberName(memberRequestDto.getMemberName())
                .email(memberRequestDto.getEmail())
                .password(memberRequestDto.getPassword())
                .department(memberRequestDto.getDepartment())
                .build();
    }

    // Entity -> Dto
    public static MemberResponseDto toMemberDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .department(member.getDepartment())
                .createAt(member.getCreatedAt())
                .build();
    }
}
