package com.example.FreshFarm.Delivery.service;

import com.example.FreshFarm.Delivery.model.dto.AuthLoginRequest;
import com.example.FreshFarm.Delivery.model.dto.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRegisterRequest request);
    AuthResponse login(AuthLoginRequest request);
}
