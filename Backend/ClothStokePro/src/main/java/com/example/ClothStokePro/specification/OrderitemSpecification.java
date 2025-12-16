package com.example.ClothStokePro.specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.example.ClothStokePro.enums.OrderStatus;

public class OrderitemSpecification {

    public static Object createdBetween(LocalDateTime startDate, LocalDateTime endDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createdBetween'");
    }

    public static Object hasItemId(Long itemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasItemId'");
    }

    @SuppressWarnings("rawtypes")
    public static Specification hasStatus(OrderStatus status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasStatus'");
    }

}
