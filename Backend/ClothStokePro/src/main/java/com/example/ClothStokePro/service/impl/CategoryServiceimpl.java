package com.example.ClothStokePro.service.impl;

import com.example.ClothStokePro.dto.AddressDto;
import com.example.ClothStokePro.dto.CategoryDto;
import com.example.ClothStokePro.dto.Response;
import com.example.ClothStokePro.entity.Category;
import com.example.ClothStokePro.exception.NotFoundException;
import com.example.ClothStokePro.Mapper.EntityDtoMapper;
import com.example.ClothStokePro.repository.CategoryRepo;
import com.example.ClothStokePro.service.interf.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceimpl<X> implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final EntityDtoMapper entityDtoMapper;




    @Override
    public Response createCategory(CategoryDto categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return Response.builder()
                .status(200)
                .message("Category created successfully")
                .build();
    }

    @Override
    public Response updateCategory(Long categoryId, CategoryDto categoryRequest) {
        final Category category = categoryRepo.findById(categoryId).orElseThrow();
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return Response.builder()
                .status(200)
                .message("category updated successfully")
                .build();
    }

    public Response getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = categories.stream()
                .map(entityDtoMapper::mapCategoryToDtoBasic)
                .collect(Collectors.toList());

        return  Response.builder()
                .status(200)
                .categoryList(categoryDtoList)
                .build();
    }

    public Response getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        CategoryDto categoryDto = entityDtoMapper.mapCategoryToDtoBasic(category);
        return Response.builder()
                .status(200)
                .category(categoryDto)
                .build();
    }

    public Response deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        categoryRepo.delete(category);
        return Response.builder()
                .status(200)
                .message("Category was deleted successfully")
                .build();
    }

    @Override
    public Response saveAndUpdateAddress(AddressDto addressDto) {
        
        throw new UnsupportedOperationException("Unimplemented method 'saveAndUpdateAddress'");
    }
}
