package com.communityconnect.spring.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static com.communityconnect.spring.model.Permission.PROJECT_CREATE;
import static com.communityconnect.spring.model.Permission.PROJECT_DELETE;
import static com.communityconnect.spring.model.Permission.PROJECT_READ;
import static com.communityconnect.spring.model.Permission.PROJECT_UPDATE;

@RequiredArgsConstructor
public enum Role {

  VOLUNTEER(Set.of(
      PROJECT_READ)),

  ORGANIZATION(Set.of(
      PROJECT_CREATE,
      PROJECT_READ,
      PROJECT_UPDATE,
      PROJECT_DELETE
      ));

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
        .stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
