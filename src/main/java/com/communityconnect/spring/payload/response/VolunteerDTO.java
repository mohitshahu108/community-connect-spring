package com.communityconnect.spring.payload.response;

import java.util.Date;
import java.util.Set;

import com.communityconnect.spring.model.Skill;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerDTO {
    private Long id;
    private String firstname;
    private String lastname;

    private String phone;
    private String location;

    private Date availabilityStartDate;

    private Date availabilityEndDate;

    private Set<Skill> skills;

    private Long userId;
    private AssetDTO profilePhoto;
}
