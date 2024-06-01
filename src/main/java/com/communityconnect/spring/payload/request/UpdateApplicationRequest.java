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
public class UpdateApplicationRequest {
    private ApplicationStatuses status;
}
