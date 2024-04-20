package com.communityconnect.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communityconnect.spring.model.Organization;
import com.communityconnect.spring.repository.OrganizationRepository;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

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
        return organizationRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Organization not found for user with id " + userId));
    }

}
