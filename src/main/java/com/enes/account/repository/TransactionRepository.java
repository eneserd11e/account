package com.enes.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enes.account.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
