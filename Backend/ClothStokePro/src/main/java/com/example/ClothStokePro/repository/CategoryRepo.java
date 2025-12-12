package com.example.ClothStokePro.repository;
import com.example.ClothStokePro.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
