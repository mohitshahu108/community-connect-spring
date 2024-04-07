package com.communityconnect.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Table(name="skills")
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    Skill(Long id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter private Long id;

    @Getter @Setter private String name;
}
