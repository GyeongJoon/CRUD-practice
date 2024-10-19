package com.example.crud.prac2.service;

import com.example.crud.prac2.domain.dto.MemberRequestDto;
import com.example.crud.prac2.domain.dto.MemberResponseDto;
import com.example.crud.prac2.domain.entity.Member;
import com.example.crud.prac2.domain.mapper.MemberMapper;
import com.example.crud.prac2.exception.ErrorCode;
import com.example.crud.prac2.exception.appException;
import com.example.crud.prac2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 조회 API
    @Transactional(readOnly = true)
    public MemberResponseDto getMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new appException(ErrorCode.MEMBER_NOT_FOUND));

        return MemberMapper.toMemberDto(member);
    }

    // 회원 등록 API
    @Transactional
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        memberRepository.findByEmail(memberRequestDto.getEmail())
                .ifPresent(member -> {
                    throw new appException(ErrorCode.MEMBER_ALREADY_EXISTS);
                });

        Member memberEntity = MemberMapper.toMemberEntity(memberRequestDto);
        memberRepository.save(memberEntity);
        return MemberMapper.toMemberDto(memberEntity);
    }

    // 회원 수정 API
    @Transactional
    public MemberResponseDto updateMember(Long memberId, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new appException(ErrorCode.MEMBER_NOT_FOUND));

        member.updateMemberInfo(memberRequestDto);
        return  MemberMapper.toMemberDto(member);
    }

    // 회원 삭제 API
    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new appException(ErrorCode.MEMBER_NOT_FOUND));

        memberRepository.delete(member);
    }
}
