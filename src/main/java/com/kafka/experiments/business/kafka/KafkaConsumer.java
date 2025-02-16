package com.kafka.experiments.business.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "meu-topico", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
