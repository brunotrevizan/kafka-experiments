package com.kafka.experiments.controller;

import com.kafka.experiments.infrastructure.kafka.producer.PurchaseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private PurchaseProducer purchaseProducer;

    @Autowired
    public PurchaseController(PurchaseProducer purchaseProducer) {
        this.purchaseProducer = purchaseProducer;
    }

    @PostMapping
    public ResponseEntity<String> produce(@RequestBody String orderJson) {
        purchaseProducer.sendMessage(orderJson);
        return ResponseEntity.ok("ok");
    }


}
