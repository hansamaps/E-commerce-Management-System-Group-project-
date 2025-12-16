package com.example.ClothStokePro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.ClothStokePro.entity.Payment;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    private BigDecimal totalPrice;
    private List<OrderitemRequest> items;
    private Payment paymentInfo;
}
