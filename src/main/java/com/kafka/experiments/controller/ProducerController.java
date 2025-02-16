package com.kafka.experiments.controller;

import com.kafka.experiments.business.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka-producer")
public class ProducerController {

    private KafkaProducer kafkaProducer;

    @Autowired
    public ProducerController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/produce")
    public ResponseEntity produce(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("ok");
    }

}
