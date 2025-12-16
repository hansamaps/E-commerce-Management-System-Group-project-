package com.example.ClothStokePro.specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.example.ClothStokePro.enums.OrderStatus;

public class OrderitemSpecification {

    @SuppressWarnings("rawtypes")
    public static Specification createdBetween(LocalDateTime startDate, LocalDateTime endDate) {

        throw new UnsupportedOperationException("Unimplemented method 'createdBetween'");
    }

    public static Object hasItemId(Long itemId) {

        throw new UnsupportedOperationException("Unimplemented method 'hasItemId'");
    }

    @SuppressWarnings("rawtypes")
    public static Specification hasStatus(OrderStatus status) {

        throw new UnsupportedOperationException("Unimplemented method 'hasStatus'");
    }

}
