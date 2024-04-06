package com.communityconnect.spring.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum Permission {

    PROJECT_READ("project:read"),
    PROJECT_UPDATE("project:update"),
    PROJECT_CREATE("project:create"),
    PROJECT_DELETE("project:delete");

    @Getter
    private final String permission;
}
