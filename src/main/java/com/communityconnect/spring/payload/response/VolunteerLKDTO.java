package com.communityconnect.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerLKDTO {
    private Long id;
    private String firstname;
    private String lastname;
}
