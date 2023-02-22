package com.account.app.unit.contorller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.account.app.api.AccountController;
import com.account.app.domain.User;
import com.account.app.domain.jsondata.AccountJsonData;
import com.account.app.service.TransactionService;
import com.account.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    String customerId = UUID.randomUUID().toString();
    User user = new User("amir", "afroozeh", "holland", "12345678");

    @Mock
    UserService userService;
    @Mock
    TransactionService transactionService;
    @InjectMocks
    AccountController accountController;

    @Test
    public void ifUserNotExitTest() {
        AccountJsonData accountJsonData = new AccountJsonData(customerId, BigDecimal.TEN);
        when(userService.findUser(customerId)).thenReturn(Optional.empty());

        ResponseEntity<String> result = accountController.openNewAccount(accountJsonData);

        assertEquals(404, result.getStatusCodeValue());
        verify(userService, never()).addNewAccount(any());
        verify(transactionService, never()).sendTransactionMessage(any());
    }

    @Test
    public void ifInitialCreditIsZeroTest() {
        when(userService.findUser(customerId)).thenReturn(Optional.of(user));
        AccountJsonData accountJsonData = new AccountJsonData(customerId, BigDecimal.ZERO);
        ResponseEntity<String> result = accountController.openNewAccount(accountJsonData);

        assertEquals(201, result.getStatusCodeValue());

        verify(userService).addNewAccount(any());
        verify(transactionService, never()).sendTransactionMessage(any());
    }

    @Test
    public void ifInitialCreditIsGreaterThanZeroTest() {
        when(userService.findUser(customerId)).thenReturn(Optional.of(user));
        AccountJsonData accountJsonData = new AccountJsonData(customerId, BigDecimal.TEN);

        ResponseEntity<?> result = accountController.openNewAccount(accountJsonData);

        assertEquals(201, result.getStatusCodeValue());
        verify(userService).addNewAccount(any());
        verify(transactionService).sendTransactionMessage(any());
    }
}
