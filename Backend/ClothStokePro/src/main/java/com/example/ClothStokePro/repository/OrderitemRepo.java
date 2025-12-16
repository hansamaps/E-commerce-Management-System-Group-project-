package com.example.ClothStokePro.repository;

import com.example.ClothStokePro.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderitemRepo extends JpaRepository<Orderitem, Long>, JpaSpecificationExecutor<Orderitem> {

}
