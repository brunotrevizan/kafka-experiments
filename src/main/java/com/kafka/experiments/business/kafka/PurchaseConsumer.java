package com.kafka.experiments.business.kafka;

import com.kafka.experiments.dto.OrderDTO;
import com.kafka.experiments.model.Customer;
import com.kafka.experiments.model.Order;
import com.kafka.experiments.model.OrderItem;
import com.kafka.experiments.service.CustomerService;
import com.kafka.experiments.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@Service
public class PurchaseConsumer {

    private CustomerService customerService;

    @Autowired
    public PurchaseConsumer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @KafkaListener(topics = "purchase-topic", groupId = "my-group")
    public void consume(String orderDTO) {
        System.out.println("Mensagem recebida: " + orderDTO);
        Customer customer = new Customer();
        customer.setName("Bruno Souza " + System.currentTimeMillis() + new Random().nextInt(999));
        customer.setEmail("bruno@" + System.currentTimeMillis() + new Random().nextInt(999) + ".com");
        customerService.createCustomer(customer);
    }
}
