package com.example.ClothStokePro.service;

import com.example.ClothStokePro.dto.AddressDto;
import com.example.ClothStokePro.dto.CategoryDto;
import com.example.ClothStokePro.dto.Response;

public interface CategoryService {
    Response saveAndUpdateAddress(AddressDto addressDto);

    Response createCategory(CategoryDto categoryRequest);

    Response updateCategory(Long categoryId, CategoryDto categoryRequest);

    Response getAllCategories();

    Response getCategoryById(Long categoryId);
}

