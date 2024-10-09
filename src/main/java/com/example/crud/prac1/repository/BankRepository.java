package com.example.crud.prac1.repository;

import com.example.crud.prac1.domain.Bank;
import com.example.crud.prac1.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findByBankNameAndCustomer(String bankName, Customer customer);
}
