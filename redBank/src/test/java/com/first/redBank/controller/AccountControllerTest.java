package com.first.redBank.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.first.redBank.model.Account;
import com.first.redBank.repository.AccountRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testOpenAccount() {
        Account newAccount = new Account();
        newAccount.setAgency("001");
        newAccount.setBalance(1000.0);

        ResponseEntity<Account> response = restTemplate.postForEntity(createURL("/api/account"), newAccount, Account.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Account openedAccount = response.getBody();
        assertThat(openedAccount).isNotNull();
        assertThat(openedAccount.getId()).isNotNull();
        assertThat(openedAccount.getAgency()).isEqualTo("001");
        assertThat(openedAccount.getBalance()).isEqualTo(1000.0);
    }

    @Test
    public void testShowAccounts() {
        ResponseEntity<List> response = restTemplate.getForEntity(createURL("/api/account"), List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private String createURL(String uri) {
        return "http://localhost:" + port + uri;
    }
}