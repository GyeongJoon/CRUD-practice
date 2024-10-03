package com.example.crud.prac1.dto;

import com.example.crud.prac1.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanRequestDto {

    private Customer customer;

    private String bankName;
}
