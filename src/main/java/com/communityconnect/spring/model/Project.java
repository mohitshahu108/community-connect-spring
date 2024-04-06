package com.communityconnect.spring.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;
    private int timeCommitment;
    private String status;

    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization organization; // here we are storing to we organization this project belongs

    @ManyToMany
    @JoinTable(
        name="ProjectSkills",
        joinColumns = @JoinColumn(name="project_id"),
        inverseJoinColumns = @JoinColumn(name="skill_id")
    )
    private Set<Skill> skills;

    @ManyToMany
    @JoinTable(
        name = "ProjectVolunteers",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "volunteer_id")
    )
    private Set<Volunteer> volunteers; //many volunteers can working same project

}
