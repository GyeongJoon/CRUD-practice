package com.example.crud.prac1.domain;

import com.example.crud.prac1.dto.CustomerRequestDto;
import com.example.crud.prac1.dto.CustomerResponseDto;

public class CustomerMapper {

    // Dto -> Entity
    public static Customer toEntity(CustomerRequestDto customerRequestDto) {
        return Customer.builder()
                .customerId(customerRequestDto.getCustomerId())
                .customerName(customerRequestDto.getCustomerName())
                .age(customerRequestDto.getAge())
                .password(customerRequestDto.getPassword())
                .build();
    }

    // Entity -> Dto
    public static CustomerResponseDto toDto(Customer customer) {
        return CustomerResponseDto.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .age(customer.getAge())
                .password(customer.getPassword())
                .build();
    }
}
