package com.account.app.service;

import com.account.app.domain.jsondata.TransactionJsonData;

public interface TransactionService {
    void sendTransactionMessage(TransactionJsonData transactionJsonData);
}
