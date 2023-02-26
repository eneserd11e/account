package com.enes.account.dto;

import java.util.stream.Collectors;

import javax.persistence.criteria.From;

import org.springframework.stereotype.Component;

import com.enes.account.model.Customer;

@Component
public class CustomerDtoConverter {
	
	private final CustomerAccountDtoConvertor converter;
	
	public CustomerDtoConverter(CustomerAccountDtoConvertor customerAccountDtoConvertor) {
		
		this.converter = customerAccountDtoConvertor;
	}

	public AccountCustomerDto convertToAccountCustomer(Customer from) {
		if(from == null) {
			return new AccountCustomerDto("", "", "");
		}
		return new AccountCustomerDto(from.getId(),from.getName(),from.getSurname());
	}
	
	public CustomerDto convertToCustomerDto(Customer from) {
		return new CustomerDto(
				from.getId(),
				from.getName(),
				from.getSurname(),
				from.getAccounts().stream().map(t-> converter.convert(t)).collect(Collectors.toSet())
				);
	}
}
