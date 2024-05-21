package com.communityconnect.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.communityconnect.spring.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {    
    Application findById(long id);
}
