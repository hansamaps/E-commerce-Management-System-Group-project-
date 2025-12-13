package com.example.ClothStokePro.dto;

import lombok.Data;

@Data
public class OrderitemRequest {

    private Long productId;
    private int quantity;
}
