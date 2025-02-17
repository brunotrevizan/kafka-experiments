package com.kafka.experiments.controller;

import com.kafka.experiments.business.kafka.KafkaProducer;
import com.kafka.experiments.business.kafka.PurchaseProducer;
import com.kafka.experiments.dto.OrderDTO;
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
    public ResponseEntity produce(@RequestBody String orderJson) {
        purchaseProducer.sendMessage(orderJson);
        return ResponseEntity.ok("ok");
    }


}
