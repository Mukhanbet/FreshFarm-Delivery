package com.example.FreshFarm.Delivery.mapper;

import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.AuthResponse;

public interface AuthMapper {
    User toUser(AuthRegisterRequest authRegisterRequest);
    AuthResponse toResponse(Long id, String token);
}
