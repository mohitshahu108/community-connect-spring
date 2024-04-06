package com.communityconnect.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.communityconnect.spring.model.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}