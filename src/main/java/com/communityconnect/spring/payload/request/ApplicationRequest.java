package com.communityconnect.spring.payload.request;

import com.communityconnect.spring.enums.ApplicationStatuses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {
    private Long id;
    private Long projectId;
    private Long volunteerId;
    private ApplicationStatuses status;
}
