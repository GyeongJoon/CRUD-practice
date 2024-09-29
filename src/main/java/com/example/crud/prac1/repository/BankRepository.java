package com.example.crud.prac1.repository;

import com.example.crud.prac1.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
