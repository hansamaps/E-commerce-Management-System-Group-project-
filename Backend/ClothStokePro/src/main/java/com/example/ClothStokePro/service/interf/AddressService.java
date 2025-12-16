package com.example.ClothStokePro.service.interf;

import com.example.ClothStokePro.dto.AddressDto;
import com.example.ClothStokePro.dto.Response;

public interface AddressService {
    Response saveAndUpdateAddress(AddressDto addressDto);
}
