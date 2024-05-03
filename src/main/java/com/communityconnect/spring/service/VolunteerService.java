package com.communityconnect.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.communityconnect.spring.model.Asset;
import com.communityconnect.spring.model.Volunteer;
import com.communityconnect.spring.repository.VolunteerRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private AssetService assetService;

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Volunteer getVolunteerById(Long id) {
        Volunteer v = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found with id " + id));
        if (v.getProfilePhoto() != null) {
            String signedUrl = s3Service.createPresignedGetUrl(v.getProfilePhoto().getId().toString());
            v.getProfilePhoto().setS3Url(signedUrl);
        }
        return v;
    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteer(Long id, Volunteer volunteerDetails) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found with id " + id));

        volunteer.setFirstname(volunteerDetails.getFirstname());
        volunteer.setLastname(volunteerDetails.getLastname());
        volunteer.setPhone(volunteerDetails.getPhone());
        volunteer.setLocation(volunteerDetails.getLocation());
        volunteer.setAvailabilityStartDate(volunteerDetails.getAvailabilityStartDate());
        volunteer.setAvailabilityEndDate(volunteerDetails.getAvailabilityEndDate());
        volunteer.setSkills(volunteerDetails.getSkills());

        return volunteerRepository.save(volunteer);
    }

    public void deleteVolunteer(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found with id " + id));

        volunteerRepository.delete(volunteer);
    }

    public Volunteer getVolunteerByUserId(Long userId) {
        Volunteer v = volunteerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found for user with id " + userId));
        if (v.getProfilePhoto() != null) {
            String signedUrl = s3Service.createPresignedGetUrl(v.getProfilePhoto().getId().toString());
            v.getProfilePhoto().setS3Url(signedUrl);
        }
        return v;
    }

    public Volunteer updateProfilePic(String assetFileName, Long assetableId,
        String assetableType, MultipartFile profilePic) throws IOException {
        // System.out.println("before");
        Asset asset = assetService.create(assetFileName, assetableId, assetableType, profilePic);
        // System.out.println("after");
        Volunteer updatedVolunteer = linkAssetWithVolunteer(assetableId, asset);
        s3Service.uploadFileToS3(asset.getId().toString(), profilePic);
        String signedUrl = s3Service.createPresignedGetUrl(asset.getId().toString());
        updatedVolunteer.getProfilePhoto().setS3Url(signedUrl);
        return updatedVolunteer;
    }


    @Transactional
    private Volunteer linkAssetWithVolunteer(Long volunteerId, Asset asset) {
        // Fetch the Volunteer entity by its ID
        Volunteer volunteer = getVolunteerById(volunteerId);
        if (volunteer == null) {
            throw new EntityNotFoundException("Volunteer with ID " + volunteerId + " not found.");
        }
    
        // Remove previous profile photo of the Volunteer
        Asset previousAsset = volunteer.getProfilePhoto();
        if (previousAsset != null) {
            // Set the profile photo to null and save the volunteer
            volunteer.setProfilePhoto(null);
            volunteerRepository.save(volunteer);

            // Delete the previous asset
            assetService.deleteAssetById(previousAsset.getId());
        }
    
        // Add new profile photo to the fetched Volunteer
        Asset savedAsset = assetService.save(asset);
        volunteer.setProfilePhoto(savedAsset);
        // Save the updated Volunteer entity back to the database
        Volunteer updatedVolunteer = volunteerRepository.save(volunteer);
    
        return updatedVolunteer;
    }
}
