package com.product.controller;

import com.product.dto.*;
import com.product.entity.Role;
import com.product.entity.Users;
import com.product.repo.RoleRepository;
import com.product.repo.UsersRepo;
import com.product.security.JwtTokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsersRepo usersRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        Role role = roleRepo.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        usersRepo.save(user);

        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());

        String accessToken = jwtTokenHelper.generateToken(userDetails);
        String refreshToken = jwtTokenHelper.generateRefreshToken(userDetails);

        return new AuthResponse(accessToken, refreshToken);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest request) {

        String username =
                jwtTokenHelper.extractUsername(request.getRefreshToken());

        if (jwtTokenHelper.isRefreshTokenExpired(request.getRefreshToken())) {
            throw new RuntimeException("Refresh token expired");
        }

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(username);

        String newAccessToken = jwtTokenHelper.generateToken(userDetails);

        return new AuthResponse(newAccessToken, request.getRefreshToken());
    }
}
