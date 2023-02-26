package com.enes.account.dto;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.enes.account.model.Account;

@Component
public class AccountDtoConverter {
	 
	private final CustomerDtoConverter customerDtoConverter;
	private final TransactionDtoConverter transactionDtoConvertor;
	public AccountDtoConverter(CustomerDtoConverter customerDtoConverter, TransactionDtoConverter transactionDtoConvertor) {
		
		this.customerDtoConverter = customerDtoConverter;
		this.transactionDtoConvertor = transactionDtoConvertor;
	}

	

	public AccountDto convert(Account from) {
		 
		return new AccountDto(from.getId(),
				from.getBalance(),
				from.getCreationDate(),
				customerDtoConverter.convertToAccountCustomer(from.getCustomer()),
				from.getTransaction()
				.stream()
				.map(t-> transactionDtoConvertor.convert(t))
				.collect(Collectors.toSet()));
	}
}
