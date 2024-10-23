package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.AuthLoginRequest;
import com.example.FreshFarm.Delivery.model.dto.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.AuthResponse;
import com.example.FreshFarm.Delivery.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthLoginRequest request) {
        return authService.login(request);
    }
}
