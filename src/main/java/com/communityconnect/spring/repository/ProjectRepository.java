package com.communityconnect.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.communityconnect.spring.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
