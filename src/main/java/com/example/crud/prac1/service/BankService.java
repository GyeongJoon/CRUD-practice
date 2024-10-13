package com.example.crud.prac1.service;

import com.example.crud.prac1.domain.Bank;
import com.example.crud.prac1.domain.BankMapper;
import com.example.crud.prac1.domain.Customer;
import com.example.crud.prac1.dto.BankRequestDto;
import com.example.crud.prac1.dto.BankResponseDto;
import com.example.crud.prac1.dto.LoanResponseDto;
import com.example.crud.prac1.exception.BankAlreadyExistsException;
import com.example.crud.prac1.exception.BankNotFoundException;
import com.example.crud.prac1.exception.CustomerNotFoundException;
import com.example.crud.prac1.repository.BankRepository;
import com.example.crud.prac1.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankService {

    private final CustomerRepository customerRepository;
    private final BankRepository bankRepository;

    // 은행 등록 API
    public ResponseEntity<String> createBank(Long customerId, BankRequestDto bankRequestDto) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("사용자 ID: " + customerId + "가 없습니다."));

        bankRepository.findByBankNameAndCustomer(bankRequestDto.getBankName(), customer)
                .ifPresent(bank -> {
                    throw new BankAlreadyExistsException(bankRequestDto.getBankName() + "이 이미 등록되어있습니다.");
                });


        Bank newBank = BankMapper.toBankEntity(bankRequestDto);
        newBank.setCustomer(customer);

        bankRepository.save(newBank);

        return ResponseEntity.ok(newBank.getBankName() + "이 등록되었습니다.");
    }

    // 은행 조회 API
    public BankResponseDto getBankById (Long customerId, Long bankId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("사용자 ID: " + customerId + "가 없습니다."));

        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new BankNotFoundException("은행 ID: " + bankId + "이 없습니다."));

        return BankMapper.toBankDto(bank, customer);
    }

    // 대출 조회 API
    public LoanResponseDto getLoanById (Long customerId, Long bankId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("사용자 ID: " + customerId + "가 없습니다."));

        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new BankNotFoundException("은행 ID: " + bankId + "이 없습니다."));

        if (bank.getLoan() >= 1000) {
            bank.setColor("빨간색");
        } else {
            bank.setColor("노란색");
        }

        return BankMapper.toLoanDto(bank, customer);
    }
}
