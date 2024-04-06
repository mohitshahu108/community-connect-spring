package com.communityconnect.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Table(name="skills")
public class Skill {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter private Long id;

    @Getter @Setter private String name;
}
