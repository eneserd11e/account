package com.enes.account.dto;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.enes.account.model.Account;

@Component
public class CustomerAccountDtoConvertor {
	
	private final TransactionDtoConverter transactionDtoConverter;
	
	public CustomerAccountDtoConvertor(TransactionDtoConverter transactionDtoConverter) {
		
		this.transactionDtoConverter = transactionDtoConverter;
	}

	public CustomerAccountDto convert(Account from) {
		
		return new CustomerAccountDto(
				from.getId(), 
				from.getBalance(),
				from.getTransaction().stream().map(t -> transactionDtoConverter.convert(t)).collect(Collectors.toSet()),
				LocalDateTime.now()
				);
	}
}
