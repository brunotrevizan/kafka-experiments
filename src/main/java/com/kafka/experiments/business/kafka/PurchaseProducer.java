package com.kafka.experiments.business.kafka;

import com.kafka.experiments.dto.OrderDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PurchaseProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PurchaseProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderDTO order) {
        kafkaTemplate.send("purchase-topic", order.toString());
    }
}
