package com.example.crud.prac2.domain.enums;

public enum CommentSortType {
    LATEST("최신순"),
    OLDEST("오래된순"),
    DEPARTMENT("부서별");

    private final String description;

    CommentSortType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
