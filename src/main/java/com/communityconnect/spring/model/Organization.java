package com.communityconnect.spring.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizations", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;
    private String website;


    // OneToMany annotation indicates that an Organization can have multiple Projects,
    // and that each Project is associated with only one Organization.
    // mappedBy attribute in OneToMany annotation is used to specify the field in the
    // Project entity that serves as the owner side of the relationship.
    // In other words, it indicates that the association is maintained in the Project entity.
    @OneToMany(mappedBy = "organization") 
    private Set<Project> projects;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
