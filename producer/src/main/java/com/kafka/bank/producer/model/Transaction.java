package com.kafka.bank.producer.model;

import com.kafka.bank.producer.model.enums.TransactionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;

    @Column(name = "origin_key")
    private String originKey;

    @Column(name = "destine_key")
    private String destineKey;

    private Double value;

    @Column(name = "transferred_at")
    private LocalDateTime transferredAt;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public Transaction() {
    }

    private Transaction(String identifier, String originKey, String destineKey, Double value, LocalDateTime transferredAt, TransactionStatus status) {
        this.identifier = identifier;
        this.originKey = originKey;
        this.destineKey = destineKey;
        this.value = value;
        this.transferredAt = transferredAt;
        this.status = status;
    }

    public static Transaction build(String identifier, String originKey, String destineKey, Double value, LocalDateTime transferredAt, TransactionStatus status){
        return new Transaction(
                identifier,
                originKey,
                destineKey,
                value,
                transferredAt,
                status
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getOriginKey() {
        return originKey;
    }

    public void setOriginKey(String originKey) {
        this.originKey = originKey;
    }

    public String getDestineKey() {
        return destineKey;
    }

    public void setDestineKey(String destineKey) {
        this.destineKey = destineKey;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getTransferredAt() {
        return transferredAt;
    }

    public void setTransferredAt(LocalDateTime transferredAt) {
        this.transferredAt = transferredAt;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}

