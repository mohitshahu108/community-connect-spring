package com.communityconnect.spring.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communityconnect.spring.enums.ApplicationStatuses;
import com.communityconnect.spring.model.Application;
import com.communityconnect.spring.model.Project;
import com.communityconnect.spring.model.Volunteer;
import com.communityconnect.spring.payload.request.ApplicationRequest;
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

    @Transactional
    public Application create(Application applicationReq) {
        try {
            Application application = applicationRepository.save(applicationReq);
            Project project = projectRepository.findById(application.getProject().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Project with id " + application.getProject().getId() + " not found"));
            project.getApplications().add(application);
            projectRepository.save(project);

            return application;
        } catch (Exception e) {
            throw new RuntimeException("Error while create application");
        }
    }

    @Transactional
    public Application updateApplication(Long id, Application app) {
        try {
            Application application = applicationRepository.findById(id).orElseThrow();
            application.setStatus(app.getStatus());

            if (app.getStatus() == ApplicationStatuses.APPROVED) {

                Project project = projectRepository.findById(app.getProject().getId())
                        .orElseThrow(() -> new RuntimeException(
                                "Project with id " + app.getProject().getId() + " not found"));

                Volunteer volunteer = volunteerRepository.findById(app.getVolunteerId())
                        .orElseThrow(() -> new RuntimeException(
                                "Volunteer with id " + app.getId() + "not found"));

                project.getVolunteers().add(volunteer);
                projectRepository.save(project);
            }

            return applicationRepository.save(application);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating application");
        }
    }
}
