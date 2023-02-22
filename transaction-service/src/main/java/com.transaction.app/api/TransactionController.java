package com.transaction.app.api;

import com.transaction.app.domain.Transaction;
import com.transaction.app.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> getTransactionById(@PathVariable("id") String id) {
        log.info("Get list of transactions for the given customerId {}", id);
        List<Transaction> transactions = transactionService.findTransactions(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
