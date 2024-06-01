package com.communityconnect.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communityconnect.spring.Application;
import com.communityconnect.spring.model.Project;
import com.communityconnect.spring.model.Volunteer;
import com.communityconnect.spring.payload.projects.request.Apply;
import com.communityconnect.spring.payload.request.ProjectDTORequest;
import com.communityconnect.spring.repository.ProjectRepository;
import com.communityconnect.spring.repository.VolunteerRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    @Autowired
    private ModelMapperService modelMapperService;


    private VolunteerRepository volunteerRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(ProjectDTORequest projectDTO) {
        Project project = modelMapperService.map(projectDTO, Project.class);
        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));
        
        return project;
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));

        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setLocation(projectDetails.getLocation());
        project.setSkills(projectDetails.getSkills());
        project.setTimeCommitment(projectDetails.getTimeCommitment());
        project.setStatus(projectDetails.getStatus());
        project.setOrganization(projectDetails.getOrganization());
        project.setSkills(projectDetails.getSkills());
        project.setVolunteers(projectDetails.getVolunteers());

        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));

        projectRepository.delete(project);
    }

    public Project apply(Apply apply) {
        Project project = projectRepository.findById(apply.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found with id " + apply.getProjectId()));
        Volunteer volunteer = volunteerRepository.findById(apply.getVolunteerId())
                .orElseThrow(() -> new RuntimeException("Volunteer not found with id " + apply.getVolunteerId()));

        project.getVolunteers().add(volunteer);

        return projectRepository.save(project);
    }

    public List<Project> getAllProjectsByOrganizationId(Long organizationId) {
        return projectRepository.findByOrganizationId(organizationId);
    }

}
