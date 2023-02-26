package com.enes.account.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.enes.account.model.Account;
import com.enes.account.model.Transaction;
import com.enes.account.repository.TransactionRepository;
 @Service
public class TransactionService {

	private Logger logger = LoggerFactory.getLogger(TransactionService.class); //slf4j spring ile beraber geliyor kullnaması çok kolay
	private final TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		
		this.transactionRepository = transactionRepository;
	}
	
	/*protected Transaction initiateMoney(final Account account,BigDecimal amount) {
		
		return transactionRepository.save(
				new Transaction(amount, account)
				);
	}*/
}
