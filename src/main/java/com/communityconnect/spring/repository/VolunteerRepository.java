package com.communityconnect.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.communityconnect.spring.model.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    /**
     * Find organization by user id.
     * @param userId - The user id to search with.
     * @return Optional<Organization> - The optional organization that was found.
     */
    Optional<Volunteer> findByUserId(Long userId);

}