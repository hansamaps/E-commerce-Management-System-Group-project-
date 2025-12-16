package com.example.ClothStokePro.service.interf;

import org.jspecify.annotations.Nullable;

import com.example.ClothStokePro.dto.AddressDto;

public interface AddressService {

    @Nullable
    Object saveAndUpdateAddress(AddressDto addressDto);

}
