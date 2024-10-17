package com.example.crud.prac2.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    MEMBER_NOT_FOUND("회원이 존재하지 않습니다.","M1"),
    MEMBER_ALREADY_EXISTS("회원이 이미 존재합니다.","M2"),
    BOARD_NOT_FOUND("게시물이 존재하지 않습니다.", "B1"),
    BOARD_ALREADY_EXISTS("게시물이 이미 존재합니다.", "B2"),
    BOARD_UPDATE_PERMISSION_DENIED("해당 게시물 수정 권한이 없습니다.", "B3"),
    BOARD_DELETE_PERMISSION_DENIED("해당 게시물 삭제 권한이 없습니다.", "B4"),
    COMMENT_NOT_FOUND("댓글이 존재하지 않습니다.", "C1"),
    COMMENT_ALREADY_EXISTS("댓글이 이미 존재합니다.", "C2"),
    COMMENT_UPDATE_PERMISSION_DENIED("해당 댓글 수정 권한이 없습니다.", "C3");

    private final String description;
    private final String id;

    ErrorCode(String description, String id) {
        this.description = description;
        this.id = id;
    }
}
