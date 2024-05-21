package com.communityconnect.spring.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.communityconnect.spring.model.Asset;
import com.communityconnect.spring.model.Organization;
import com.communityconnect.spring.repository.OrganizationRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private AssetService assetService;

    public List<Organization> getAllOrganization() {
        return organizationRepository.findAll();
    }

    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found with id" + id));
    }

    public Organization updateOrganization(Long id, Organization organizationDetails) {

        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found with id" + id));
        organization.setLocation(organizationDetails.getLocation());
        organization.setDescription(organizationDetails.getDescription());
        organization.setName(organizationDetails.getName());
        organization.setProjects(organizationDetails.getProjects());
        organization.setWebsite(organizationDetails.getWebsite());

        return organizationRepository.save(organization);
    }

    public void deleteOrganization(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found with id" + id));
        organizationRepository.delete(organization);
    }

    public Organization getOrganizationByUserId(Long userId) {
        Organization org = organizationRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Organization not found for user with id " + userId));
        if (org.getProfilePhoto() != null) {
            String signedUrl = s3Service.createPresignedGetUrl(org.getProfilePhoto().getId().toString());
            org.getProfilePhoto().setS3Url(signedUrl);
        }
        return org;
    }

    public Organization updateProfilePic(String assetFileName, Long assetableId,
            String assetableType, MultipartFile profilePic) throws IOException {
        // System.out.println("before");
        Asset asset = assetService.create(assetFileName, assetableId, assetableType, profilePic);
        // System.out.println("after");
        Organization updatedOrganization = linkAssetWithOrganization(assetableId, asset);
        s3Service.uploadFileToS3(asset.getId().toString(), profilePic);
        String signedUrl = s3Service.createPresignedGetUrl(asset.getId().toString());
        updatedOrganization.getProfilePhoto().setS3Url(signedUrl);
        return updatedOrganization;
    }

    @Transactional
    private Organization linkAssetWithOrganization(Long orgnaizationId, Asset asset) {
        // Fetch the Volunteer entity by its ID
        Organization organization = getOrganizationById(orgnaizationId);
        if (organization == null) {
            throw new EntityNotFoundException("Volunteer with ID " + orgnaizationId + " not found.");
        }

        // Remove previous profile photo of the Volunteer
        Asset previousAsset = organization.getProfilePhoto();
        if (previousAsset != null) {
            // Set the profile photo to null and save the volunteer
            organization.setProfilePhoto(null);
            organizationRepository.save(organization);

            // Delete the previous asset
            assetService.deleteAssetById(previousAsset.getId());
        }

        // Add new profile photo to the fetched Organization
        Asset savedAsset = assetService.save(asset);
        organization.setProfilePhoto(savedAsset);
        // Save the updated Volunteer entity back to the database
        Organization updateorgOrganization = organizationRepository.save(organization);

        return updateorgOrganization;
    }
}
