package com.communityconnect.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.communityconnect.spring.model.Asset;
import com.communityconnect.spring.payload.response.AssetDTO;
import com.communityconnect.spring.service.AssetService;
import com.communityconnect.spring.service.ModelMapperService;

@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private ModelMapperService modelMapperService;

    @GetMapping
    public ResponseEntity<List<AssetDTO>> getAllAssets() {
        List<Asset> assets = assetService.findAll();
        List<AssetDTO> assetDTOs = modelMapperService.mapToList(assets, AssetDTO.class);
        return ResponseEntity.ok(assetDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDTO> getAssetById(@PathVariable Long id) {
        Asset asset = assetService.findById(id);
        AssetDTO assetDTO = modelMapperService.map(asset, AssetDTO.class);
        return ResponseEntity.ok(assetDTO);
    }

    @PostMapping
    public ResponseEntity<AssetDTO> createAsset(@RequestBody AssetDTO assetDTO) {
        AssetDTO savedAssetDTO = modelMapperService
                .map(assetService.save(modelMapperService.map(assetDTO, Asset.class)), AssetDTO.class);
        return ResponseEntity.ok(savedAssetDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetDTO> updateAsset(@PathVariable Long id, @RequestBody AssetDTO assetDTO) {
        Asset updatedAsset = modelMapperService.map(assetDTO, Asset.class);
        updatedAsset.setId(id);
        Asset updated = assetService.save(updatedAsset);
        AssetDTO updatedAssetDTO = modelMapperService.map(updated, AssetDTO.class);
        return ResponseEntity.ok(updatedAssetDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}