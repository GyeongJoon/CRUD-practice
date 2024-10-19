package com.example.crud.prac2.domain.enums;

import lombok.Getter;

@Getter
public enum Department {

    DEVELOPMENT("개발 부서", 1L),
    HR("인사 부서", 2L),
    DESIGN("디자인 부서", 3L);

    private final String description;
    private final Long id;

    private Department(String description, Long id){
        this.description = description;
        this.id = id;
    }
}
