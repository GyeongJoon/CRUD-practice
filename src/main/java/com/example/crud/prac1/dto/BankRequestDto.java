package com.example.crud.prac1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankRequestDto {

    private String bankName;

    private double loan;

    private double installmentSavings;
}
