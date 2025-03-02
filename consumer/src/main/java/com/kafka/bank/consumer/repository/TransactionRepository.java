package com.kafka.bank.consumer.repository;

import com.kafka.bank.consumer.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t WHERE t.identifier = :identifier")
    public Optional<Transaction> findByIdentifier(@Param("identifier") String identifier);
}
