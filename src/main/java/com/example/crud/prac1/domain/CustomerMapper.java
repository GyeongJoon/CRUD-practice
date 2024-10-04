package com.example.crud.prac1.domain;

import com.example.crud.prac1.dto.CustomerRequestDto;
import com.example.crud.prac1.dto.CustomerResponseDto;

public class CustomerMapper {
    // 유저 DTO -> Entity
    public static Customer toCustomerEntity(CustomerRequestDto customerRequestDto) {
        return Customer.builder()
                .customerName(customerRequestDto.getCustomerName())
                .age(customerRequestDto.getAge())
                .password(customerRequestDto.getPassword())
                .build();
    }

    // 유저 Entity -> DTO
    public static CustomerResponseDto toCustomerDto(Customer customer) {
        return CustomerResponseDto.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .age(customer.getAge())
                .password(customer.getPassword())
                .build();
    }
}
