package com.transaction.app.service;

import com.transaction.app.domain.Account;
import com.transaction.app.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public void updateAccountBalance(String accountId, BigDecimal amount) {
        Optional<Account> foundAccount = accountRepository.findById(accountId);
        if (foundAccount.isEmpty()) {
            log.error("Account with id {} not found", accountId);
            return;
        }

        Account account = foundAccount.get();
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }
}
