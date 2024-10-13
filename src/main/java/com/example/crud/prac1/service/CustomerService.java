package com.example.crud.prac1.service;

import com.example.crud.prac1.domain.Customer;
import com.example.crud.prac1.domain.CustomerMapper;
import com.example.crud.prac1.dto.CustomerRequestDto;
import com.example.crud.prac1.dto.CustomerResponseDto;
import com.example.crud.prac1.exception.CustomerAlreadyExistsException;
import com.example.crud.prac1.exception.CustomerNotFoundException;
import com.example.crud.prac1.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // 유저 등록 API
    @Transactional
    public ResponseEntity<String> createCustomer(CustomerRequestDto customerRequestDto) {

        customerRepository.findByCustomerName(customerRequestDto.getCustomerName())
                .ifPresent(customer -> {
                    throw new CustomerAlreadyExistsException(customerRequestDto.getCustomerName() + "님이 이미 등록되어있습니다..");
                });

        Customer newCustomer = CustomerMapper.toCustomerEntity(customerRequestDto);

        customerRepository.save(newCustomer);

        return ResponseEntity.ok(customerRequestDto.getCustomerName() + "님이 등록되었습니다.");
    }

    // 유저 조회 API
    @Transactional(readOnly = true)
    public CustomerResponseDto getCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("사용자 ID: " + customerId + "님이 없습니다."));

        return CustomerMapper.toCustomerDto(customer);
    }

}
