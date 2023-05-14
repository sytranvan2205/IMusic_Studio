package com.imusicstudio.service.serviceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.imusicstudio.service.CloudinaryService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryServiceImpl implements CloudinaryService {
    private Cloudinary cloudinary;

    public CloudinaryServiceImpl() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dwbnrx0mg");
        config.put("api_key", "849922747851736");
        config.put("api_secret", "LIjweVHhRwkW5zL-8GFT6tY7E0Y");
        this.cloudinary = new Cloudinary(config);
    }
    @Override
    public String uploadImage(MultipartFile file) {
        File uploadedFile = null;
        uploadedFile = convertMultipartFileToFile(file);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.asMap("folder", "webnhaccu"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        uploadedFile.delete();
        return uploadResult.get("url").toString();
    }
    @Override
    public boolean deleteImage(String imageUrl) {
        URL url = null;
        boolean result = false;
        try {
            url = new URL(imageUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        String filename = url.getPath().substring(url.getPath().lastIndexOf("/") + 1);
        String publicId = filename.substring(0, filename.lastIndexOf('.'));
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("folder", "webnhaccu"));
            result =  true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public File convertMultipartFileToFile(MultipartFile file)  {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convFile;
    }

}
