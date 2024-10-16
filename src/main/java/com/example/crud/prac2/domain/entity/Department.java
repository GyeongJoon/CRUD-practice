package com.example.crud.prac2.domain.entity;

public enum Department {

    DEVELOPMENT("개발 부서", 1L),
    HR("인서 부서", 2l),
    DESIGN("디자인 부서", 3L);

    private final String department;
    private final Long id;

    Department(String department, Long id){
        this.department = department;
        this.id = id;
    }
}
