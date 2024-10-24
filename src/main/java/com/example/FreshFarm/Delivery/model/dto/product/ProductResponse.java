package com.example.FreshFarm.Delivery.model.dto.product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponse {
    private Long id;
    private String farmer;
    private String name;
    private String description;
    private double price;
    private int stock;
    private LocalDateTime createdAt;
}
