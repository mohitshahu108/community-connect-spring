package com.communityconnect.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.communityconnect.spring.model.Project;
import com.communityconnect.spring.payload.projects.request.Apply;
import com.communityconnect.spring.payload.request.ProjectDTORequest;
import com.communityconnect.spring.payload.response.ProjectDTOResponse;
import com.communityconnect.spring.service.ModelMapperService;
import com.communityconnect.spring.service.ProjectService;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModelMapperService modelMapperService;

    @GetMapping
    public ResponseEntity<List<ProjectDTOResponse>> getAllProjects(
        @RequestParam(value = "organizationId", required = false) Long organizationId
    ) {
        List<Project> projects = null;
        if (organizationId != null) {
            projects = projectService.getAllProjectsByOrganizationId(organizationId);
        } else {
            projects = projectService.getAllProjects();
        }
        List<ProjectDTOResponse> projectDTOs = modelMapperService.mapToList(projects, ProjectDTOResponse.class);
        return ResponseEntity.ok(projectDTOs);
    }

    @PostMapping
    public ResponseEntity<ProjectDTOResponse> createProject(@RequestBody ProjectDTORequest projectDTO) {
        return ResponseEntity.ok(modelMapperService.map(projectService.createProject(projectDTO), ProjectDTOResponse.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTOResponse> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(modelMapperService.map(projectService.getProjectById(id), ProjectDTOResponse.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTOResponse> updateProject(@PathVariable Long id, @RequestBody ProjectDTORequest projectDetails) {
        return ResponseEntity.ok(modelMapperService.map(projectService.updateProject(id, modelMapperService.map(projectDetails, Project.class)), ProjectDTOResponse.class));
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

    @PutMapping("/{id}/apply")
    public ResponseEntity<ProjectDTOResponse> apply(@PathVariable Long id, @RequestBody Apply apply) { 
        return ResponseEntity.ok(modelMapperService.map(projectService.apply(apply), ProjectDTOResponse.class));
    }
}
