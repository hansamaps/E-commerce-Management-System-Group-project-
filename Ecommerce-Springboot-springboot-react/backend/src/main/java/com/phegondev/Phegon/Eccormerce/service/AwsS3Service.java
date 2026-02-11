package com.phegondev.Phegon.Eccormerce.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class AwsS3Service {

    @Value("${file.upload.dir:uploads}")
    private String uploadDir;

    public String saveImageToS3(MultipartFile photo) {
        try {
            // Create uploads directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename
            String originalFilename = photo.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID() + fileExtension;

            // Save file
            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.write(filePath, photo.getBytes());

            log.info("Image saved locally: {}", uniqueFilename);
            return "/uploads/" + uniqueFilename;

        } catch (IOException e) {
            log.error("Unable to save image locally: {}", e.getMessage());
            throw new RuntimeException("Unable to save image locally: " + e.getMessage());
        }
    }
}