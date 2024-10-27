package com.example.FreshFarm.Delivery.service;

import com.example.FreshFarm.Delivery.model.domain.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image save(MultipartFile image);
    void delete(String filename);
}
