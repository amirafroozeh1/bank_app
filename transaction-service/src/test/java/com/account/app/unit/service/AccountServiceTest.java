package com.account.app.unit.service;

import com.transaction.app.domain.Account;
import com.transaction.app.domain.User;
import com.transaction.app.repository.AccountRepository;
import com.transaction.app.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    Account account = new Account("newAccountId", new User(), BigDecimal.TEN);
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    AccountServiceImpl accountServiceImpl;

    @Test
    public void updateBalanceWhenUserNotFoundTest() {
        when(accountRepository.findById("accountId")).thenReturn(Optional.empty());

        accountServiceImpl.updateAccountBalance("accountId", BigDecimal.TEN);

        verify(accountRepository).findById("accountId");
        verify(accountRepository, never()).save(any());
    }

    @Test
    public void updateBalanceWhenUserFoundTest() {
        when(accountRepository.findById("accountId")).thenReturn(Optional.of(account));

        accountServiceImpl.updateAccountBalance("accountId", BigDecimal.TEN);

        verify(accountRepository).findById("accountId");
        verify(accountRepository).save(any());
    }
}
