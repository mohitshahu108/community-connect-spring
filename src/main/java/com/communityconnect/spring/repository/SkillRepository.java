package com.communityconnect.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.communityconnect.spring.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
