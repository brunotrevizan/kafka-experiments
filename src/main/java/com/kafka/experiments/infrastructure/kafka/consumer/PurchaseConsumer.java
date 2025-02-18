package com.kafka.experiments.infrastructure.kafka.consumer;

import com.kafka.experiments.dto.OrderParser;
import com.kafka.experiments.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PurchaseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseConsumer.class);

    private OrderService orderService;
    private OrderParser orderParser;

    @Autowired
    public PurchaseConsumer(OrderService orderService, OrderParser orderParser) {
        this.orderService = orderService;
        this.orderParser = orderParser;
    }

    @KafkaListener(topics = "purchase-topic", groupId = "purchase-group")
    public void consume(String orderJson) {
        try {
            orderService.createOrderFromOrderDTO(orderParser.parseOrderJson(orderJson));
        } catch (Exception e) {
            logger.error("Error processing order: {}", orderJson, e);
        }
    }
}
