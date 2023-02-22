package com.account.app.unit.contorller;

import com.transaction.app.api.TransactionController;
import com.transaction.app.domain.Transaction;
import com.transaction.app.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {
    String customerId = UUID.randomUUID().toString();
    Transaction transaction = new Transaction("transactionId", "customerId", "creditAccountId", "debitAccountId", BigDecimal.TEN, "description");
    @Mock
    TransactionService transactionService;
    @InjectMocks
    TransactionController transactionController;

    @Test
    public void getTransactionByIdTest() {
        when(transactionService.findTransactions(customerId)).thenReturn(List.of(transaction));

        ResponseEntity<List<Transaction>> result = transactionController.getTransactionById(customerId);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(List.of(transaction), result.getBody());
        verify(transactionService).findTransactions(customerId);
    }
}
