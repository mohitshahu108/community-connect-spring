package com.communityconnect.spring.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communityconnect.spring.enums.ApplicationStatuses;
import com.communityconnect.spring.model.Application;
import com.communityconnect.spring.model.Project;
import com.communityconnect.spring.model.Volunteer;
import com.communityconnect.spring.payload.request.ApplicationRequest;
import com.communityconnect.spring.payload.request.UpdateApplicationRequest;
import com.communityconnect.spring.repository.ApplicationRepository;
import com.communityconnect.spring.repository.ProjectRepository;
import com.communityconnect.spring.repository.VolunteerRepository;

import jakarta.transaction.Transactional;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private ModelMapperService modelMapperService; 

    @Transactional
    public Application create(ApplicationRequest applicationReq) {
        try {
            Volunteer volunteer = volunteerRepository.findById(applicationReq.getVolunteerId())
                    .orElseThrow(() -> new RuntimeException(
                            "Volunteer with id " + applicationReq.getVolunteerId() + " not found"));
            Application application = applicationRepository.save(modelMapperService.map(applicationReq, Application.class));
            application.setVolunteer(volunteer);
            applicationRepository.save(application);
            return application;
        } catch (Exception e) {
            throw new RuntimeException("Error while create application");
        }
    }

    @Transactional
    public Application updateApplication(Long id, UpdateApplicationRequest app) {
        try {
            Application application = applicationRepository.findById(id).orElseThrow();
            application.setStatus(app.getStatus());

            if (app.getStatus() == ApplicationStatuses.APPROVED) {

                Project project = projectRepository.findById(application.getProjectId())
                        .orElseThrow(() -> new RuntimeException(
                                "Project with id " + application.getProjectId() + " not found"));

                Volunteer volunteer = volunteerRepository.findById(application.getVolunteer().getId())
                        .orElseThrow(() -> new RuntimeException(
                                "Volunteer with id " + application.getVolunteer().getId() + "not found"));

                project.getVolunteers().add(volunteer);
                projectRepository.save(project);
            }

            return applicationRepository.save(application);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
