package com.kafka.bank.consumer.repository;

import com.kafka.bank.consumer.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeyRepository extends JpaRepository<Key, Integer> {

    @Query("SELECT k FROM Key k WHERE k.key = :key")
    Optional<Key> findByKey(@Param("key") String key);
}
