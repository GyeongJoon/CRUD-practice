package com.example.crud.prac1.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanResponseDto {

    private String bankName;

    private double loan;

    private String color;

    private String customerName;
}
