package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.auth.AuthLoginRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthResponse;
import com.example.FreshFarm.Delivery.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@ModelAttribute AuthRegisterRequest request) {
        AuthResponse response = authService.register(request);
        return "redirect:/pages/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute AuthLoginRequest request) {
        AuthResponse response = authService.login(request);
        return "redirect:/pages/register";
    }
}
