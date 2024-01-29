package com.first.redBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.redBank.model.Account;
import com.first.redBank.repository.AccountRepository;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	@GetMapping
	public List<Account> showAccounts() {
		return accountRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Account> openAccount(@RequestBody Account account) {
		Account openedAccount = accountRepository.save(account);
		return new ResponseEntity<>(openedAccount, HttpStatus.CREATED);
	}

}
