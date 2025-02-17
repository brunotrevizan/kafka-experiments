package com.kafka.experiments.business.kafka;

import com.kafka.experiments.dto.OrderDTO;
import com.kafka.experiments.dto.OrderParser;
import com.kafka.experiments.model.Customer;
import com.kafka.experiments.model.Order;
import com.kafka.experiments.model.OrderItem;
import com.kafka.experiments.service.CustomerService;
import com.kafka.experiments.service.OrderService;
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

    private OrderService orderService;
    private OrderParser orderParser;

    @Autowired
    public PurchaseConsumer(OrderService orderService, OrderParser orderParser) {
        this.orderService = orderService;
        this.orderParser = orderParser;
    }

    @KafkaListener(topics = "purchase-topic", groupId = "purchase-group")
    public void consume(String orderJson) throws IOException {
//        int randomInt = new Random().nextInt(2);
        int randomInt = 1;
        try {
            System.out.println("Integer deu: " + randomInt);

            if(randomInt  == 1) {
                throw new IOException();
            }
            orderService.createOrderFromOrderDTO(orderParser.parseOrderJson(orderJson));
        }catch (Exception e) {
            System.out.println(" -> Deu Erro, vai entrar em retry");
            throw e;
        }
    }
}
