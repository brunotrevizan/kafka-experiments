package com.kafka.experiments.business.kafka;

import com.kafka.experiments.business.kafka.config.KafkaConsumerConfig;
import com.kafka.experiments.dto.OrderDTO;
import com.kafka.experiments.dto.OrderParser;
import com.kafka.experiments.model.Customer;
import com.kafka.experiments.model.Order;
import com.kafka.experiments.model.OrderItem;
import com.kafka.experiments.service.CustomerService;
import com.kafka.experiments.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
