package com.example.ClothStokePro.service.impl;

import com.example.ClothStokePro.Mapper.EntityDtoMapper;
import com.example.ClothStokePro.dto.LoginRequestDto;
import com.example.ClothStokePro.dto.Response;
import com.example.ClothStokePro.dto.UserDto;
import com.example.ClothStokePro.entity.User;
import com.example.ClothStokePro.enums.UserRole;
import com.example.ClothStokePro.exception.InvalidCredentialException;
import com.example.ClothStokePro.exception.NotFoundException;
import com.example.ClothStokePro.repository.UserRepo;
import com.example.ClothStokePro.security.JwtUtils;
import com.example.ClothStokePro.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response registerUser(UserDto registrationRequest) {

        UserRole role = UserRole.USER;
        if (registrationRequest.getRole() != null &&
            registrationRequest.getRole().equalsIgnoreCase("admin")) {
            role = UserRole.ADMIN;
        }

        User user = User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .phoneNumber(registrationRequest.getPhoneNumber())
                .role(role)
                .build();

        User savedUser = userRepo.save(user);

        UserDto userDto = entityDtoMapper.mapUserToDtoBasic(savedUser);

        return Response.builder()
                .status(200)
                .message("User Successfully Added")
                .user(userDto)
                .build();
    }

    @Override
    public Response loginUser(LoginRequestDto loginRequest) {

        User user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("Email not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("Password does not match");
        }

        String token = jwtUtils.generateToken(user);

        return Response.builder()
                .status(200)
                .message("User Successfully Logged In")
                .token(token)
                .expirationTime("6 Month")
                .role(user.getRole().name())
                .build();
    }

    @Override
    public Response getAllUsers() {

        List<UserDto> userDtos = userRepo.findAll()
                .stream()
                .map(entityDtoMapper::mapUserToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .userList(userDtos)
                .build();
    }

    @Override
    public User getLoginUser() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();
        log.info("User Email is: {}", email);

        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Response getUserInfoAndOrderHistory() {

        User user = getLoginUser();
        UserDto userDto =
                entityDtoMapper.mapUserToDtoPlusAddressAndOrderHistory(user);

        return Response.builder()
                .status(200)
                .user(userDto)
                .build();
    }
}
