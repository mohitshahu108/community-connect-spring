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
public class ProjectDTO {

    private Long id;

    private String name;
    private String description;
    private String location;
    private int timeCommitment;
    private String status;

    private Long organizationId;  // Include only the organization ID
    private Skill[] skills;  // Use an array of Long for skill IDs

    private Long[] volunteerIds;  // Use an array of Long for volunteer IDs
}
