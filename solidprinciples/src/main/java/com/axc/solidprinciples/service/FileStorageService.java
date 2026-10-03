package com.axc.solidprinciples.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service 
public class FileStorageService {

    private final Path fileStorageLocation = Paths.get("uploads/kyc");

    public String storeFile(MultipartFile file, String fileName) {
        
        try {

            Files.createDirectories(fileStorageLocation);

            Path destionationPath = fileStorageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), destionationPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            return fileName;
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }

    }
}
