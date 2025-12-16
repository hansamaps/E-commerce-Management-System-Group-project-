package com.example.ClothStokePro.repository;

import com.example.ClothStokePro.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
