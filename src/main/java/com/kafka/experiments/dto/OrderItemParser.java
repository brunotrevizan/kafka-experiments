package com.kafka.experiments.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kafka.experiments.model.Order;
import com.kafka.experiments.model.OrderItem;
import com.kafka.experiments.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderItemParser {

    public OrderItem parseOrderItem(ItemDTO item, Order order, Product product) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setQuantity(item.quantity());
        orderItem.setTotal(item.subtotal());
        orderItem.setProduct(product);
        return orderItem;
    }

}
