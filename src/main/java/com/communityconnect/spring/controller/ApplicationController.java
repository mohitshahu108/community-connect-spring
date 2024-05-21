package com.communityconnect.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communityconnect.spring.model.Application;
import com.communityconnect.spring.payload.request.ApplicationRequest;
import com.communityconnect.spring.payload.response.ApplicationResponse;
import com.communityconnect.spring.service.ApplicationService;
import com.communityconnect.spring.service.ModelMapperService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/apply")
    public ResponseEntity<Object> apply(
            @RequestBody ApplicationRequest applicationRequest
            ) {
        try {
            Application application = applicationService.create(modelMapperService.map(applicationRequest, Application.class));
            return ResponseEntity.ok(modelMapperService.map(application, ApplicationResponse.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } 

    @PostMapping("/{id}")
    public ResponseEntity<Object> updateApplication(
            @PathVariable Long id,
            @RequestBody ApplicationRequest applicationRequest
            ) {
        try {
            Application application = applicationService.updateApplication(id, modelMapperService.map(applicationRequest, Application.class));
            return ResponseEntity.ok(modelMapperService.map(application, ApplicationResponse.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } 

}
