package com.example.ClothStokePro.controller;

import com.example.ClothStokePro.dto.AddressDto;
import com.example.ClothStokePro.dto.Response;
import com.example.ClothStokePro.service.interf.AddressService;
import lombok.RequiredArgsConstructor;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/save")
    public ResponseEntity<@Nullable Object> saveAndUpdateAddress(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.saveAndUpdateAddress(addressDto));
    }
}
