package com.marketlow.products.domain.model;

import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Product {
    private final String uui;
    private final String name;
    private final String description;
    private final Double price;
    private final Integer stock;
    private final String category;
    private final Boolean state;
}
