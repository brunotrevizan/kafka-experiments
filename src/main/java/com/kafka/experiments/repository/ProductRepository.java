package com.kafka.experiments.repository;

import com.kafka.experiments.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
