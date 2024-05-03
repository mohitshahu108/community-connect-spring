package com.communityconnect.spring.service;

import com.communityconnect.spring.model.Asset;
import com.communityconnect.spring.repository.AssetRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private S3Service s3Service;

    public Asset generateS3UrlForAsset(Asset asset) {
        String s3Url = s3Service.createPresignedGetUrl(asset.getId().toString());
        asset.setS3Url(s3Url);
        return asset;
    }

    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public Asset findById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }

    public Asset save(Asset asset) {
        return assetRepository.save(asset);
    }

    public void deleteById(Long id) {
        assetRepository.deleteById(id);
    }

    public Asset create(String assetFileName, Long assetableId, String assetableType,
            MultipartFile profilePic) {
        // Validate input parameters
        if (assetFileName == null || assetFileName.isEmpty() || assetableId == null || assetableType == null
                || assetableType.isEmpty() || profilePic == null || profilePic.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        // Calculate assetContentType and assetFileSize
        String assetContentType = profilePic.getContentType();
        long assetFileSize = profilePic.getSize();

        // Create a new asset using Lombok's @Builder
        Asset asset = Asset.builder()
                .assetFileName(assetFileName)
                .assetableId(assetableId)
                .assetableType(assetableType)
                .assetContentType(assetContentType) // Set the content type
                .assetFileSize(assetFileSize) // Set the file size, casting to int as assetFileSize is an Integer in your model
                .s3Url("new_file_url") // Placeholder, replace with actual file handling logic
                .build();

        // Save the new asset
        return assetRepository.save(asset);
    }

    public Asset getAssetById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asset with ID " + id + " not found."));
    }
 
    public void deleteAssetById(Long id) {
        Asset asset = getAssetById(id);
        assetRepository.delete(asset);
    }

}