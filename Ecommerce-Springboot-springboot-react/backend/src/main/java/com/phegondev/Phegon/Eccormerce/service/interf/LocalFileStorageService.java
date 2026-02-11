package com.phegondev.Phegon.Eccormerce.service.interf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@SuppressWarnings("unused")
@Service
@Slf4j
public class LocalFileStorageService {

    @Value("${file.upload.dir:uploads}")
    private String uploadDir;

    public String saveImageLocally(MultipartFile file) {
        try {
            // Create uploads directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID() + fileExtension;

            // Save file
            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.write(filePath, file.getBytes());

            return "/uploads/" + uniqueFilename;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to save image locally: " + e.getMessage());
        }
    }
}