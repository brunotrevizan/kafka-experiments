package com.kafka.experiments.infrastructure.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PurchaseProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PurchaseProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String orderJson) {
        kafkaTemplate.send("purchase-topic", orderJson);
    }
}
