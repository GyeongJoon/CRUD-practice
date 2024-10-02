package com.example.crud.prac1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {

    private Long loanId;

    private String loanName;

    private int loanAmount;

    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankId")
    private Bank bank;
}
