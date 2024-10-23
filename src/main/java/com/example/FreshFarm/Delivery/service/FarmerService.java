package com.example.FreshFarm.Delivery.service;

import com.example.FreshFarm.Delivery.model.dto.farmer.FarmerRequest;
import com.example.FreshFarm.Delivery.model.dto.farmer.FarmerResponse;

import java.util.List;

public interface FarmerService {
    List<FarmerResponse> all(int page, int size);
    FarmerResponse getProfile(String token);
    FarmerResponse getById(Long id);
    FarmerResponse update(String token, FarmerRequest request);
    void delete(String token);
}
