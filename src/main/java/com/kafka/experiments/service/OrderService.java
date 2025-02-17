package com.kafka.experiments.service;

import com.kafka.experiments.dto.ItemDTO;
import com.kafka.experiments.dto.OrderDTO;
import com.kafka.experiments.dto.OrderItemParser;
import com.kafka.experiments.dto.OrderParser;
import com.kafka.experiments.model.Order;
import com.kafka.experiments.model.OrderItem;
import com.kafka.experiments.model.Product;
import com.kafka.experiments.repository.CustomerRepository;
import com.kafka.experiments.repository.OrderRepository;
import com.kafka.experiments.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderItemParser orderItemParser;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository, OrderItemParser orderItemParser) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderItemParser = orderItemParser;
    }

    public Order createOrderFromOrderDTO(OrderDTO orderDTO) {
        Order order = createOrderObject(orderDTO);
        return orderRepository.save(order);
    }

    private Order createOrderObject(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomer(customerRepository.getReferenceById(orderDTO.customerId()));
        order.setOrderDate(orderDTO.orderDate());
        order.setStatus(orderDTO.status());
        order.setTotal(orderDTO.total());
        order.setOrderItems(orderDTO.orderItems().stream().map((item) -> createOrderItemObject(item, order)).toList());
        return order;
    }

    private OrderItem createOrderItemObject(ItemDTO itemDTO, Order order) {
        Product product = productRepository.getReferenceById(Long.valueOf(itemDTO.productId()));
        return orderItemParser.parseOrderItem(itemDTO, order, product);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

}
