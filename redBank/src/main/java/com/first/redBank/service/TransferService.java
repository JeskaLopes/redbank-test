package com.first.redBank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.redBank.model.Account;
import com.first.redBank.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public boolean handleTransfer(Long idOriginAccount, Long idDestinyAccount, double value) {
        return accountRepository.findById(idOriginAccount)
            .filter(originAccount -> "Ativa".equals(originAccount.getStatus()))
            .flatMap(originAccount -> accountRepository.findById(idDestinyAccount)
                .filter(destinyAccount -> "Ativa".equals(destinyAccount.getStatus()))
                .filter(destinyAccount -> originAccount.getBalance() >= value)
                .map(destinyAccount -> {
                    originAccount.setBalance(originAccount.getBalance() - value);
                    destinyAccount.setBalance(destinyAccount.getBalance() + value);
                    accountRepository.saveAll(List.of(originAccount, destinyAccount));
                    return true; // Transferência bem-sucedida
                }))
            .orElse(false); // Transferência falhou
    }
    
    public boolean isAccountActive(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        return accountOptional.map(account -> "Ativa".equals(account.getStatus())).orElse(false);
    }

    public boolean hasSufficientBalance(Long accountId, double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        return accountOptional.map(account -> account.getBalance() >= amount).orElse(false);
    }
}
