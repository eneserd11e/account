package com.enes.account.service;

import org.springframework.stereotype.Service;

import com.enes.account.dto.CustomerDto;
import com.enes.account.dto.CustomerDtoConverter;
import com.enes.account.exception.CustomerNotFoundException;
import com.enes.account.model.Customer;
import com.enes.account.repository.CustomerRepository;
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerDtoConverter converter;
	
	
	public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {

		this.customerRepository = customerRepository;
		this.converter = converter;
	}

	//serviceler arası veri transferlerinde entity kullanılabilir ama bunu yaparken methodumuzu protected yapmalıyız.
	//sadece serviseler kullanabilsin diye encapsulation
	protected Customer findCustomerById(String id) {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer could not find by id:" + id));
	}

	public CustomerDto getCustomerById(String customerId) {
		// TODO Auto-generated method stub
		return converter.convertToCustomerDto(findCustomerById(customerId));
	}
	
}
