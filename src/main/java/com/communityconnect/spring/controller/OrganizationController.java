package com.communityconnect.spring.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.communityconnect.spring.model.Organization;
import com.communityconnect.spring.payload.response.OrganizationDTO;
import com.communityconnect.spring.payload.response.VolunteerDTO;
import com.communityconnect.spring.service.ModelMapperService;
import com.communityconnect.spring.service.OrganizationService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ModelMapperService modelMapperService;

    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> getAllOrganization() {
        return ResponseEntity.ok(modelMapperService.mapToList(organizationService.getAllOrganization(), OrganizationDTO.class));
    }

    // @PostMapping // will not create organization directly it will be create with user creation 
    // public Organization createOrganization(@RequestBody Organization organization) {
    //     return organizationService.createOrganization(organization);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable Long id) {
        return ResponseEntity
                .ok(modelMapperService.map(organizationService.getOrganizationById(id), OrganizationDTO.class));
    }

    
    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<OrganizationDTO> getOrganizationByUserId(@PathVariable Long userId) {
        System.out.println("Userid"+userId);
        Organization organization = organizationService.getOrganizationByUserId(userId);
        if (organization == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modelMapperService.map(organization, OrganizationDTO.class));
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable Long id, @RequestBody OrganizationDTO organizationDetails) {
        Organization organization = modelMapperService.map(organizationDetails, Organization.class);
        Organization updatedOrganization = organizationService.updateOrganization(id, organization);
        return ResponseEntity.ok(modelMapperService.map(updatedOrganization, OrganizationDTO.class));
    }

    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
    }

    @PostMapping(value = "/profile_pic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<OrganizationDTO> updateProfilePic(
            @RequestParam("assetFileName") String assetFileName,
            @RequestParam("assetableId") Long assetableId,
            @RequestParam("assetableType") String assetableType,
            @RequestParam("profilePic") MultipartFile profilePic) throws IOException {
        // Use the new method from VolunteerService to handle the update and return the
        Organization organization = organizationService.updateProfilePic(assetFileName, assetableId, assetableType,
                profilePic);
        // DTO
        OrganizationDTO responseDTO = modelMapperService.map(organization, OrganizationDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

}