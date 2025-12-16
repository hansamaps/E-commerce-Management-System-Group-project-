package com.example.ClothStokePro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.ClothStokePro.entity.Orderitem;
import com.example.ClothStokePro.entity.Payment;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    private BigDecimal totalPrice;
    @SuppressWarnings("unused")
    private List<OrderitemRequest> items;
    private Payment paymentInfo;

    public Collection<Orderitem> getitems() {

        throw new UnsupportedOperationException("Unimplemented method 'getitems'");
    }

    public Collection<Orderitem> getItems() {

        throw new UnsupportedOperationException("Unimplemented method 'getItems'");
    }
}
