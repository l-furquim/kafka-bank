package com.kafka.bank.consumer.service;

import com.kafka.bank.consumer.dto.TransactionDto;
import com.kafka.bank.consumer.model.Transaction;
import com.kafka.bank.consumer.model.enums.TransactionStatus;
import com.kafka.bank.consumer.repository.KeyRepository;
import com.kafka.bank.consumer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    @Autowired
    private  TransactionRepository transactionRepository;

    @Autowired
    private  KafkaTemplate<String, TransactionDto> kafkaTemplate;

    @Autowired
    private  KeyRepository keyRepository;



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

    @Transactional
    public Transaction validate(String identifier,String originKey, String destineKey){
        var transaction = transactionRepository.findByIdentifier(identifier);

        var origin = keyRepository.findByKey(originKey);
        var destine = keyRepository.findByKey(destineKey);

        if(origin.isEmpty() || destine.isEmpty()){
           transaction.get().setStatus(TransactionStatus.ERRO);
        }else{
            transaction.get().setStatus(TransactionStatus.PROCESSADO);
        }

        transactionRepository.save(transaction.get());

        return transaction.get();
    }

}
