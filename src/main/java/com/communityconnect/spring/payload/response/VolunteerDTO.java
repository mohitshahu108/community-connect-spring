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

    private String firstname;
    private String lastname;

    private String phone;
    private String location;

    @JsonProperty("availability_start_date")
    private Date availabilityStartDate;

    @JsonProperty("availability_end_date")
    private Date availabilityEndDate;

    private Set<Skill> skills;

    @JsonProperty("user_id")
    private Long userId;
}
