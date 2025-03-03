package com.kafka.bank.consumer.exception;

public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException(String message) {
        super(message);
    }
}
