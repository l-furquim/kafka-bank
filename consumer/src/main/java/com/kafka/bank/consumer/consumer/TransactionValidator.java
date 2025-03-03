package com.kafka.bank.consumer.consumer;

import com.kafka.bank.consumer.dto.TransactionDto;
import com.kafka.bank.consumer.exception.KeyNotFoundException;
import com.kafka.bank.consumer.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionValidator {

    private static final String TOPIC = "transaction.request";

    @Autowired
    private TransactionService transactionService;

    @KafkaListener(topics = TOPIC, groupId = "grupo")
    @RetryableTopic(
            backoff = @Backoff(value = 3000L),
            attempts = "5",
            include = KeyNotFoundException.class
    )
    public void transactionProcess(TransactionDto data){
        log.info("Transação recebida {}", data.toString());

        var transaction = transactionService.validate(data.getIdentifier(), data.getChaveOrigem(), data.getChaveDestino());

        log.info("Transação processada {}", transaction.toString());
    }

}
