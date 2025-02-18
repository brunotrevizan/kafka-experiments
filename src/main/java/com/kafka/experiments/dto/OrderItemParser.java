package com.kafka.experiments.dto;

import com.kafka.experiments.model.Order;
import com.kafka.experiments.model.OrderItem;
import com.kafka.experiments.model.Product;
import org.springframework.stereotype.Component;


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
