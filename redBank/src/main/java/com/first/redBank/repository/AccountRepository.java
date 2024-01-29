package com.first.redBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.first.redBank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	 Account findByAgency(String agency);
}
