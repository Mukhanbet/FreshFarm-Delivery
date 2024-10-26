package com.example.FreshFarm.Delivery.repository;

import com.example.FreshFarm.Delivery.model.domain.Farmer;
import com.example.FreshFarm.Delivery.model.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByFarmer(Farmer farmer, Pageable pageable);
    @Query(value = "SELECT * FROM products_tb WHERE name LIKE %:name%", nativeQuery = true)
    List<Product> findAllByName(@Param("name") String name, Pageable of);
}
