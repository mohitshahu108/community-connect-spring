package com.communityconnect.spring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communityconnect.spring.model.Volunteer;
import com.communityconnect.spring.repository.VolunteerRepository;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public List<Volunteer> getAllVolunteers() {
        List<Volunteer> list = volunteerRepository.findAll();
        System.out.println("list" + list);
        return list; 
    }

    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found with id " + id));
    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteer(Long id, Volunteer volunteerDetails) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found with id " + id));

        volunteer.setFirstname(volunteerDetails.getFirstname());
        volunteer.setLastname(volunteerDetails.getLastname());
        volunteer.setPhone(volunteerDetails.getPhone());
        volunteer.setLocation(volunteerDetails.getLocation());
        volunteer.setAvailabilityStartDate(volunteerDetails.getAvailabilityStartDate());
        volunteer.setAvailabilityEndDate(volunteerDetails.getAvailabilityEndDate());
        volunteer.setSkills(volunteerDetails.getSkills());

        return volunteerRepository.save(volunteer);
    }

    public void deleteVolunteer(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found with id " + id));

        volunteerRepository.delete(volunteer);
    }
}
