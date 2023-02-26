package com.enes.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enes.account.dto.CustomerDto;
import com.enes.account.service.CustomerService;

@RestController
@RequestMapping("/v1/customer/")
public class CustomerController {
	
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		
		this.customerService = customerService;
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String customerId){
		
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
	
	
}
