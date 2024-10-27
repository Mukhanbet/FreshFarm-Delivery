package com.example.FreshFarm.Delivery.service.impl;

import com.example.FreshFarm.Delivery.config.JwtService;
import com.example.FreshFarm.Delivery.exception.CustomException;
import com.example.FreshFarm.Delivery.mapper.ProductMapper;
import com.example.FreshFarm.Delivery.model.domain.Farmer;
import com.example.FreshFarm.Delivery.model.domain.Image;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.product.ProductRequest;
import com.example.FreshFarm.Delivery.model.dto.product.ProductResponse;
import com.example.FreshFarm.Delivery.repository.FarmerRepository;
import com.example.FreshFarm.Delivery.repository.ProductRepository;
import com.example.FreshFarm.Delivery.service.ImageService;
import com.example.FreshFarm.Delivery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final FarmerRepository farmerRepository;
    private final JwtService jwtService;
    private final ImageService imageService;

    @Override
    public List<ProductResponse> all(int page, int size) {
        return productMapper.toResponseList(productRepository.findAll(PageRequest.of(page, size)).getContent());
    }

    @Override
    public List<ProductResponse> farmerProducts(Long farmerId, int page, int size) {
        Farmer farmer = farmerRepository.findById(farmerId).orElseThrow(() -> new CustomException("Farmer not found", HttpStatus.NOT_FOUND));
        return productMapper.toResponseList(productRepository.findAllByFarmer(farmer, PageRequest.of(page, size)));
    }

    @Override
    public List<ProductResponse> findByName(String name, int page, int size) {
        return productMapper.toResponseList(productRepository.findAllByName(name, PageRequest.of(page, size)));
    }

    @Override
    public List<ProductResponse> sortBy(String sortBy, String sortType, int page, int size) {
        if (sortType.equals("asc")) {
            return productMapper.toResponseList(productRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).ascending())).getContent());
        } else {
            return productMapper.toResponseList(productRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())).getContent());
        }
    }

    @Override
    public List<ProductResponse> getOwnProducts(String token, int page, int size) {
        User user = jwtService.getUserFromToken(token);
        return productMapper.toResponseList(productRepository.findAllByFarmer(user.getFarmer(), PageRequest.of(page, size)));
    }

    @Override
    public ProductResponse getDetail(Long id) {
        return productMapper.toResponse(productRepository.findById(id).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public ProductResponse add(String token, ProductRequest productRequest, List<MultipartFile> images) {
        User user = jwtService.getUserFromToken(token);
        Product product = productMapper.toProduct(new Product(), user.getFarmer(), productRequest);
        List<Image> productImages = new ArrayList<>();
        for (MultipartFile file : images) {
            productImages.add(imageService.save(file));
        }
        product.setImages(productImages);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse update(String token, Long id, ProductRequest productRequest) {
        User user = jwtService.getUserFromToken(token);
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        return productMapper.toResponse(productRepository.save(productMapper.toProduct(product, user.getFarmer(), productRequest)));
    }

    @Override
    public void delete(String token, Long id) {
        User user = jwtService.getUserFromToken(token);
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        if (!product.getFarmer().equals(user.getFarmer())) {
            throw new CustomException("You are not allowed to delete this product", HttpStatus.UNAUTHORIZED);
        }
        for (Image image : product.getImages()) {
            imageService.delete(image.getFilename());
        }
        productRepository.delete(product);
    }
}
