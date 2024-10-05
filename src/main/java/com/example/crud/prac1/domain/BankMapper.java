package com.example.crud.prac1.domain;

import com.example.crud.prac1.dto.BankRequestDto;
import com.example.crud.prac1.dto.BankResponseDto;
import com.example.crud.prac1.dto.LoanResponseDto;

public class BankMapper {
    // 은행 DTO -> Entity
    public static Bank toBankEntity(BankRequestDto bankRequestDto) {
        return Bank.builder()
                .bankName(bankRequestDto.getBankName())
                .installmentSavings(bankRequestDto.getInstallmentSavings())
                .loan(bankRequestDto.getLoan())
                .build();
    }

    // 은행 Entity -> DTO
    public static BankResponseDto toBankDto(Bank bank, Customer customer) {
        return BankResponseDto.builder()
                .bankId(bank.getBankId())
                .bankName(bank.getBankName())
                .installmentSavings(bank.getInstallmentSavings())
                .customerName(customer.getCustomerName())
                .build();
    }

    // 대출 Entity -> DTO
    public static LoanResponseDto toLoanDto(Bank bank, Customer customer) {
        return LoanResponseDto.builder()
                .bankName(bank.getBankName())
                .loan(bank.getLoan())
                .color(bank.getColor())
                .customerName(customer.getCustomerName())
                .build();
    }
}
