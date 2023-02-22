package com.account.app.unit.service;

import com.transaction.app.domain.Transaction;
import com.transaction.app.domain.jsondata.TransactionJsonData;
import com.transaction.app.repository.TransactionRepository;
import com.transaction.app.service.AccountService;
import com.transaction.app.service.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    AccountService accountService;
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;

    @Test
    public void getTransactionMessageFromSameAccountTest() {
        TransactionJsonData transactionJsonData = new TransactionJsonData("customer_1", "account_1", "account_1", BigDecimal.TEN, "new transaction");

        transactionServiceImpl.getTransactionMessage(transactionJsonData);

        verify(accountService).updateAccountBalance("account_1", BigDecimal.TEN);
        verify(transactionRepository).save(any());
    }

    @Test
    public void getTransactionMessageFromDifferentAccountTest() {
        TransactionJsonData transactionJsonData = new TransactionJsonData("customer_1", "account_1", "account_2", BigDecimal.TEN, "new transaction");

        transactionServiceImpl.getTransactionMessage(transactionJsonData);

        verify(accountService).updateAccountBalance("account_1", BigDecimal.TEN);
        verify(accountService).updateAccountBalance("account_2", BigDecimal.TEN.negate());
        verify(transactionRepository).save(any());
    }

    @Test
    public void findTransactionsTest() {
        Transaction transaction = new Transaction();
        when(transactionRepository.findByCustomerId("customerId")).thenReturn(List.of(transaction));

        List<Transaction> result = transactionServiceImpl.findTransactions("customerId");

        verify(transactionRepository).findByCustomerId("customerId");
        assertEquals(List.of(transaction), result);
    }
}
