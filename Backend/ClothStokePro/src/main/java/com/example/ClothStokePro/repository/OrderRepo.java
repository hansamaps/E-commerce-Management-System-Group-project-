package com.example.ClothStokePro.repository;

import com.example.ClothStokePro.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
