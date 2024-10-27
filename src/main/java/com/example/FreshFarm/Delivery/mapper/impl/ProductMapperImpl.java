package com.example.FreshFarm.Delivery.mapper.impl;

import com.example.FreshFarm.Delivery.mapper.ProductMapper;
import com.example.FreshFarm.Delivery.model.domain.Farmer;
import com.example.FreshFarm.Delivery.model.domain.Image;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.dto.product.ProductRequest;
import com.example.FreshFarm.Delivery.model.dto.product.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product toProduct(Product product, Farmer farmer, ProductRequest request) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setFarmer(farmer);
        return product;
    }

    @Override
    public ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setFarmer(product.getFarmer().getUserDetails().getName());
        response.setName(product.getName());
        for (Image image : product.getImages()) {
            response.getImagePath().add(image.getPath());
        }
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        return response;
    }

    @Override
    public List<ProductResponse> toResponseList(List<Product> products) {
        List<ProductResponse> responseList = new ArrayList<>();
        for (Product product : products) {
            responseList.add(toResponse(product));
        }
        return responseList;
    }
}
