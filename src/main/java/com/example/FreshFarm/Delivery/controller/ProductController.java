package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.product.ProductRequest;
import com.example.FreshFarm.Delivery.model.dto.product.ProductResponse;
import com.example.FreshFarm.Delivery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> allProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long farmerId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc") String sortType
    ) {
        if (farmerId != null) {
            return productService.farmerProducts(farmerId, page, size);
        } else if (name != null) {
            return productService.findByName(name, page, size);
        } else if (sortBy != null) {
            return productService.sortBy(sortBy, sortType, page, size);
        } else {
            return productService.all(page, size);
        }
    }

    @GetMapping("/my")
    public List<ProductResponse> getOwnProducts(
            @RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return productService.getOwnProducts(token, page, size);
    }

    @GetMapping("/{id}")
    public ProductResponse getDetail(@PathVariable Long id) {
        return productService.getDetail(id);
    }

    @PostMapping
    public ProductResponse add(
            @RequestHeader("Authorization") String token,
            @RequestBody ProductRequest productRequest
    ) {
        return productService.add(token, productRequest);
    }

    @PutMapping("/{id}")
    public ProductResponse update(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductRequest productRequest
    ) {
        return productService.update(token, id, productRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {
        productService.delete(token, id);
    }
}
