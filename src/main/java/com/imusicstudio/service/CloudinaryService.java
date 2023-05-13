package com.imusicstudio.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface CloudinaryService {
    String uploadImage(MultipartFile file);

     boolean deleteImage(String imageUrl);

    File convertMultipartFileToFile(MultipartFile file);
}
