package com.example.ClothStokePro.security;

import com.example.ClothStokePro.entity.User;
import com.example.ClothStokePro.exception.NotFoundException;
import com.example.ClothStokePro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username)
                .orElseThrow();

        return AuthUser.builder()
                .user(user)
                .build();
    }
}

