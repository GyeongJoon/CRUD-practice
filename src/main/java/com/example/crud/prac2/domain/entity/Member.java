package com.example.crud.prac2.domain.entity;

import com.example.crud.prac2.domain.dto.MemberRequestDto;
import com.example.crud.prac2.domain.enums.Department;
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
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    // update 메소드
    public void updateMemberInfo(MemberRequestDto memberRequestDto) {
        if (memberRequestDto.getMemberName() != null) {
            this.memberName = memberRequestDto.getMemberName();
        }
        if (memberRequestDto.getEmail() != null) {
            this.email = memberRequestDto.getEmail();
        }
        if (memberRequestDto.getPassword() != null) {
            this.password = memberRequestDto.getPassword();
        }
        if (memberRequestDto.getDepartment() != null) {
            this.department = memberRequestDto.getDepartment();
        }
    }
}
