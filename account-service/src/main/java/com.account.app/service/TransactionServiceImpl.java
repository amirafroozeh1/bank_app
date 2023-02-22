package com.account.app.service;

import com.account.app.domain.jsondata.TransactionJsonData;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Setter
public class TransactionServiceImpl implements TransactionService {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendTransactionMessage(TransactionJsonData transactionJsonData) {
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, transactionJsonData);
    }
}