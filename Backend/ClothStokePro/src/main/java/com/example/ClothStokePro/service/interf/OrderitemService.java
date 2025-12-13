package com.example.ClothStokePro.service.interf;

import com.example.ClothStokePro.dto.OrderRequest;
import com.example.ClothStokePro.dto.Response;
import com.example.ClothStokePro.enums.OrderStatus;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface OrderitemService {
    Response placeOrder(OrderRequest orderRequest);

    Response updateOrderItemStatus(Long orderItemId, String status);

    Response filterOrderItems(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate, Long itemId,
            Pageable pageable);
}
