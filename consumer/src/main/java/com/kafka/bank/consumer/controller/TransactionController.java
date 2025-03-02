package com.kafka.bank.consumer.controller;

import com.kafka.bank.consumer.dto.TransactionDto;
import com.kafka.bank.consumer.model.enums.TransactionStatus;
import com.kafka.bank.consumer.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDto> create(@RequestBody TransactionDto request){
        request.setIdentifier(UUID.randomUUID().toString());
        request.setDataTransferencia(LocalDateTime.now());
        request.setStatus(TransactionStatus.EM_PROCESSAMENTO);


        var transaction = transactionService.create(request);

        return ResponseEntity.ok().body(transaction);
    }
}
