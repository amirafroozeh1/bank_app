package com.transaction.app.service;

import com.transaction.app.domain.Transaction;
import com.transaction.app.domain.jsondata.TransactionJsonData;

import java.util.List;

public interface TransactionService {
    void getTransactionMessage(TransactionJsonData transactionJsonData);

    List<Transaction> findTransactions(String id);
}
