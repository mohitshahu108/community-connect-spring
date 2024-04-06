
package com.communityconnect.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "volunteers")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String firstname;
    private String lastname;

    private String phone;
    private String location;

    @Temporal(TemporalType.DATE)
    private Date availabilityStartDate;

    @Temporal(TemporalType.DATE)
    private Date availabilityEndDate;

    @ManyToMany
    @JoinTable(name = "VolunteerSkills", joinColumns = @JoinColumn(name = "volunteer_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
