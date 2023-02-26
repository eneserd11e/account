package com.enes.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enes.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

}
