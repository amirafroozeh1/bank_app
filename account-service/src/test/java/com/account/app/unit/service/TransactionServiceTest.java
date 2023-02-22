package com.account.app.unit.service;

import static org.mockito.Mockito.*;

import com.account.app.domain.jsondata.TransactionJsonData;
import com.account.app.service.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    RabbitTemplate rabbitTemplate;
    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;

    @Test
    public void sendTransactionMessageTest() {
        TransactionJsonData transactionJsonData = new TransactionJsonData("customer_1", "account_1", "account_2", BigDecimal.TEN, "new transaction");
        transactionServiceImpl.setExchange("fake_exchange");
        transactionServiceImpl.setRoutingJsonKey("fake_key");

        transactionServiceImpl.sendTransactionMessage(transactionJsonData);

        verify(rabbitTemplate).convertAndSend("fake_exchange", "fake_key", transactionJsonData);
    }
}
