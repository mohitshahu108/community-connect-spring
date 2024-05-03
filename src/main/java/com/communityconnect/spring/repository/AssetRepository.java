package com.communityconnect.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.communityconnect.spring.model.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {    
}
