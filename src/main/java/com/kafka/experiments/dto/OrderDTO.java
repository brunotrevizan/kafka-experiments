package com.kafka.experiments.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
    Long customerId,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime orderDate,
    double total,
    String status,
    @JsonProperty("orderItems")
    List<ItemDTO> orderItems) {
}
