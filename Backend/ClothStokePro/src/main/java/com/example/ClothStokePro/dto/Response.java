package com.example.ClothStokePro.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Response {

    private int status;
    private String message;
    private String token;
    private String expirationTime;
    private String role;

    private UserDto user;
    private List<UserDto> userList;
}
