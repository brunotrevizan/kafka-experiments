package com.kafka.experiments.dto;

import java.util.List;

public record OrderDTO(
    Long customerId,
    double total,
    List<ItemDTO> itens) {
}
