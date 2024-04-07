package com.communityconnect.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user", uniqueConstraints = {
  @UniqueConstraint(columnNames = "email")
})
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;

  @Enumerated(EnumType.STRING)
  @Autowired
  private Role role;

  /**
   * The `@OneToMany` annotation is used to define a one-to-many relationship
   * in JPA. In this case, it defines a one-to-many relationship from the
   * `User` entity to the `Token` entity. The `mappedBy` attribute is used to
   * specify the name of the property in the `Token` entity that is used to
   * establish the relationship.
   *
   * The `mappedBy` attribute is used to tell JPA that the relationship is
   * "mapped by" the `user` property in the `Token` entity. This means that
   * JPA will look for a property named `user` in the `Token` entity, and
   * use that property to establish the relationship.
   *
   * In other words, this annotation tells JPA that the `User` entity has a
   * one-to-many relationship with the `Token` entity, where the relationship
   * is established by the `user` property in the `Token` entity.
   */
  @OneToMany(mappedBy = "user") 
  private List<Token> tokens;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // if (role == null) {
    //   return Collections.emptyList();
    // }
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
