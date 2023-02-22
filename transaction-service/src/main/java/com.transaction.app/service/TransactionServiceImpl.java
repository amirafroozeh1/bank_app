package com.transaction.app.service;

import com.transaction.app.domain.Transaction;
import com.transaction.app.domain.jsondata.TransactionJsonData;
import com.transaction.app.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Slf4j
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    @Override
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void getTransactionMessage(TransactionJsonData transactionJsonData) {
        log.info("Received transaction message");
        String creditAccountId = transactionJsonData.getCreditAccountId();
        String debitAccountId = transactionJsonData.getDebitAccountId();

        if (creditAccountId.equals(debitAccountId)) {
            accountService.updateAccountBalance(creditAccountId, transactionJsonData.getAmount());
        } else {
            accountService.updateAccountBalance(creditAccountId, transactionJsonData.getAmount());
            accountService.updateAccountBalance(debitAccountId, transactionJsonData.getAmount().negate());
        }

        log.info("Save transaction");
        transactionRepository.save(new Transaction(transactionJsonData.getCustomerId(), transactionJsonData.getCreditAccountId(), transactionJsonData.getDebitAccountId(), transactionJsonData.getAmount(), transactionJsonData.getDescription()));
    }

    @Override
    public List<Transaction> findTransactions(String id) {
        return transactionRepository.findByCustomerId(id);
    }
}