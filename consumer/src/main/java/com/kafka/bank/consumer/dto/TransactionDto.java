package com.kafka.bank.consumer.dto;

import com.kafka.bank.consumer.model.enums.TransactionStatus;
import java.time.LocalDateTime;


public class TransactionDto {
    private String identifier;
    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;
    private LocalDateTime dataTransferencia;
    private TransactionStatus status;

    public TransactionDto(String identifier, String chaveOrigem, String chaveDestino, Double valor, LocalDateTime dataTransferencia, TransactionStatus status) {
        this.identifier = identifier;
        this.chaveOrigem = chaveOrigem;
        this.chaveDestino = chaveDestino;
        this.valor = valor;
        this.dataTransferencia = dataTransferencia;
        this.status = status;
    }

    public TransactionDto() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getChaveOrigem() {
        return chaveOrigem;
    }

    public void setChaveOrigem(String chaveOrigem) {
        this.chaveOrigem = chaveOrigem;
    }

    public String getChaveDestino() {
        return chaveDestino;
    }

    public void setChaveDestino(String chaveDestino) {
        this.chaveDestino = chaveDestino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
