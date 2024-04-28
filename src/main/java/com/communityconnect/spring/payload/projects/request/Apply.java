package com.communityconnect.spring.payload.projects.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apply {
    private Long projectId;
    private Long volunteerId;
}
