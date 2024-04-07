package com.communityconnect.spring.payload.response;

import com.communityconnect.spring.model.Project;
import com.communityconnect.spring.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO {
    private Long id;

    private String name;
    private String description;
    private String location;
    private String website;
    private Set<Project> projects;
    private Long userId;
}
