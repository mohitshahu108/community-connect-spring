package com.communityconnect.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communityconnect.spring.model.Project;
import com.communityconnect.spring.payload.response.ProjectDTO;
import com.communityconnect.spring.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    @Autowired
    private ModelMapperService modelMapperService;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(ProjectDTO projectDTO) {
        Project project = modelMapperService.map(projectDTO, Project.class);
        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));
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
}
