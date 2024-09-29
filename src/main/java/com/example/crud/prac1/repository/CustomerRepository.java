package com.example.crud.prac1.repository;

import com.example.crud.prac1.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
