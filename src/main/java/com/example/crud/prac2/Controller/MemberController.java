package com.example.crud.prac2.Controller;

import com.example.crud.prac2.domain.dto.MemberRequestDto;
import com.example.crud.prac2.domain.dto.MemberResponseDto;
import com.example.crud.prac2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    // 회원 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMemberId(@PathVariable Long memberId) {
        MemberResponseDto responseDto = memberService.getMemberId(memberId);
        return ResponseEntity.ok(responseDto);
    }

    // 회원 등록
    @PostMapping
    public ResponseEntity<String> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto responseDto = memberService.createMember(memberRequestDto);
        return ResponseEntity.ok(responseDto.getMemberName() + "님이 회원가입 되었습니다.");
    }

    // 회원 수정
    @PutMapping("/{memberId}")
    public ResponseEntity<String> updateMember(@PathVariable Long memberId,
                                               @RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto responseDto = memberService.updateMember(memberId, memberRequestDto);
        return ResponseEntity.ok(responseDto.getMemberName() + "님의 정보가 수정되었습니다.");
    }

    // 회원 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok("해당 회원이 탈퇴하였습니다.");
    }
}
