package com.kafka.bank.producer.service;

import com.kafka.bank.producer.dto.TransactionDto;
import com.kafka.bank.producer.model.Transaction;
import com.kafka.bank.producer.repository.TransactionRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final KafkaTemplate<String, TransactionDto> kafkaTemplate;

    public TransactionService(TransactionRepository transactionRepository, KafkaTemplate<String, TransactionDto> kafkaTemplate) {
        this.transactionRepository = transactionRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public TransactionDto create(TransactionDto data){
        kafkaTemplate.send(data.getIdentifier(), data);

        transactionRepository.save(
                Transaction.build(
                        data.getIdentifier(),
                        data.getChaveOrigem(),
                        data.getChaveDestino(),
                        data.getValor(),
                        data.getDataTransferencia(),
                        data.getStatus()
                )
        );

        return data;
    }
}
