package com.kafka.experiments.repository;

import com.kafka.experiments.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
