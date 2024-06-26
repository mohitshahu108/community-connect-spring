package com.communityconnect.spring.payload.response;

import com.communityconnect.spring.model.Skill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTOResponse {
    private Long id;

    private String name;
    private String description;
    private String location;
    private int timeCommitment;
    private String status;
    private OrganizationDTO organization;
    private Skill[] skills;  // Use an array of Long for skill IDs
    private ApplicationResponse[] applications;
    private VolunteerLKDTO[] volunteers;  // Use an array of Long for volunteer IDs
}
