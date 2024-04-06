package com.communityconnect.spring.controller;
import org.springframework.web.bind.annotation.RestController;

import com.communityconnect.spring.model.Organization;
import com.communityconnect.spring.service.OrganizationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public List<Organization> getAllOrganization() {
        return organizationService.getAllOrganization();
    }

    // @PostMapping // will not create organization directly it will be create with user creation 
    // public Organization createOrganization(@RequestBody Organization organization) {
    //     return organizationService.createOrganization(organization);
    // }

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable Long id) {
        return organizationService.getOrganizationById(id);
    }

    @PutMapping("/{id}")
    public Organization updateOrganization(@PathVariable Long id, @RequestBody Organization organizationDetails) {
        return organizationService.updateOrganization(id, organizationDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
    }

}