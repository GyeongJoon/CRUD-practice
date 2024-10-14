package com.example.crud.prac1.controller;

import com.example.crud.prac1.dto.BankRequestDto;
import com.example.crud.prac1.dto.BankResponseDto;
import com.example.crud.prac1.dto.LoanResponseDto;
import com.example.crud.prac1.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("customer/{customerId}/bank")
public class BankController {

    private final BankService bankService;

    // 은행 등록
    @PostMapping
    public ResponseEntity<String> createBank(@PathVariable Long customerId, @RequestBody BankRequestDto bankRequestDto) {
        ResponseEntity<String> response = bankService.createBank(customerId, bankRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
    }

    // 은핻 조회
    @GetMapping("/{bankId}/name")
    public ResponseEntity<BankResponseDto> getBankById(@PathVariable Long customerId, @PathVariable Long bankId) {
        BankResponseDto bankResponseDto = bankService.getBankById(customerId, bankId);
        return ResponseEntity.ok(bankResponseDto);
    }

    // 대출 조회
    @GetMapping("/{bankId}/loan")
    public ResponseEntity<LoanResponseDto> getLoanById(@PathVariable Long customerId, @PathVariable Long bankId) {
        LoanResponseDto bankResponseDto = bankService.getLoanById(customerId, bankId);
        return ResponseEntity.ok(bankResponseDto);
    }
}