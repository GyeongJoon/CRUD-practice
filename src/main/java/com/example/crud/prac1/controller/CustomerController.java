package com.example.crud.prac1.controller;

import com.example.crud.prac1.dto.CustomerRequestDto;
import com.example.crud.prac1.dto.CustomerResponseDto;
import com.example.crud.prac1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    // 유저 등록
    @PostMapping
    public ResponseEntity<String> creteCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        ResponseEntity<String> response = customerService.createCustomer(customerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
    }

    // 유저 조회
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long customerId) {
        CustomerResponseDto customerResponseDto = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerResponseDto);
    }
}
