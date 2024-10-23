package com.example.FreshFarm.Delivery.service.impl;

import com.example.FreshFarm.Delivery.config.JwtService;
import com.example.FreshFarm.Delivery.exception.CustomException;
import com.example.FreshFarm.Delivery.mapper.FarmerMapper;
import com.example.FreshFarm.Delivery.model.domain.Farmer;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.farmer.FarmerRequest;
import com.example.FreshFarm.Delivery.model.dto.farmer.FarmerResponse;
import com.example.FreshFarm.Delivery.repository.FarmerRepository;
import com.example.FreshFarm.Delivery.repository.UserRepository;
import com.example.FreshFarm.Delivery.service.FarmerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FarmerServiceImpl implements FarmerService {
    private final FarmerRepository farmerRepository;
    private final FarmerMapper farmerMapper;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public List<FarmerResponse> all(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return farmerMapper.toResponseList(farmerRepository.findAll(pageable).getContent());
    }

    @Override
    public FarmerResponse getProfile(String token) {
        User user = jwtService.getUserFromToken(token);
        return farmerMapper.toResponse(user.getFarmer());
    }

    @Override
    public FarmerResponse getById(Long id) {
        Farmer farmer = farmerRepository.findById(id).orElseThrow(() -> new CustomException("Farmer not found", HttpStatus.NOT_FOUND));
        return farmerMapper.toResponse(farmer);
    }

    @Override
    public FarmerResponse update(String token, FarmerRequest request) {
        User user = jwtService.getUserFromToken(token);
        return farmerMapper.toResponse(farmerRepository.save(farmerMapper.toFarmer(user.getFarmer(), request)));
    }

    @Override
    public void delete(String token) {
        User user = jwtService.getUserFromToken(token);
        farmerRepository.delete(user.getFarmer());
    }
}
