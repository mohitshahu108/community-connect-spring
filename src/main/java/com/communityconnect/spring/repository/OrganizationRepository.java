package com.communityconnect.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.communityconnect.spring.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
