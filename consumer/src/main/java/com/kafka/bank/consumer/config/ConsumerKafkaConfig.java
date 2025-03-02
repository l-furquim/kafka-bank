package com.kafka.bank.consumer.config;


import com.kafka.bank.consumer.dto.TransactionDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerKafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapAddress;

    @Bean
    public ConsumerFactory<String, TransactionDto> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);

        var jsonDeserializer = new JsonDeserializer<>(TransactionDto.class);

        jsonDeserializer.forKeys()
                .trustedPackages(("*"));

        var stringDeserializer = new StringDeserializer();

        return new DefaultKafkaConsumerFactory<>(props,stringDeserializer, jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransactionDto>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, TransactionDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}