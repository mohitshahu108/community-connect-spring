package com.communityconnect.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.communityconnect.spring.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    
    /**
     * Find organization by user id.
     * @param userId - The user id to search with.
     * @return Optional<Organization> - The optional organization that was found.
     */
    Optional<Organization> findByUserId(Long userId);
}
