package com.example.crud.prac1.domain;

import com.example.crud.prac1.dto.BankRequestDto;
import com.example.crud.prac1.dto.BankResponseDto;
import com.example.crud.prac1.dto.LoanRequestDto;
import com.example.crud.prac1.dto.LoanResponseDto;

public class BankMapper {

    // 은행 DTO -> Entity
    public static Bank toBankEntity(BankRequestDto bankRequestDto, Customer customer) {
        return Bank.builder()
                .bankId(bankRequestDto.getBankId())
                .bankName(bankRequestDto.getBankName())
                .loan(bankRequestDto.getLoan())
                .installmentSavings(bankRequestDto.getInstallmentSavings())
                .customer(customer)
                .build();
    }

    // 은행 Entity -> DTO
    public static BankResponseDto toBankDto(Bank bank) {
        return BankResponseDto.builder()
                .bankId(bank.getBankId())
                .bankName(bank.getBankName())
                .build();
    }

    // 대출 DTO -> Entity
    public static Bank toLoanEntity(LoanRequestDto loanRequestDto) {
        return Bank.builder()
                .customer(loanRequestDto.getCustomer())
                .bankName(loanRequestDto.getBankName())
                .build();
    }

    // 대출 Entity -> DTO
    public static LoanResponseDto toLoanDto(Bank bank, Customer customer) {
        return LoanResponseDto.builder()
                .customerName(customer.getCustomerName())
                .bankName(bank.getBankName())
                .loan(bank.getLoan())
                .build();
    }
}
