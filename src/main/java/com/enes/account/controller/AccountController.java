package com.enes.account.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enes.account.dto.AccountDto;
import com.enes.account.dto.CreateAccountRequest;
import com.enes.account.service.AccountService;

@RestController
@RequestMapping("/v1/account")//versiyonlama apıları güncellersin ama eski apıları başka biri kullnayordur. oyuzden versiyonlama önemli olabilir.
public class AccountController {
	
	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		
		this.accountService = accountService;
	}
	
	@PostMapping
	public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody CreateAccountRequest request){
		
		return ResponseEntity.ok(accountService.createAccount(request));
	}
}
