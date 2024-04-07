package com.communityconnect.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communityconnect.spring.model.Volunteer;
import com.communityconnect.spring.payload.response.VolunteerDTO;
import com.communityconnect.spring.service.ModelMapperService;
import com.communityconnect.spring.service.VolunteerService;

@RestController
@RequestMapping("api/v1/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private ModelMapperService modelMapperService; 


    @GetMapping
    public ResponseEntity<List<VolunteerDTO>> getAllVolunteers() {
        return ResponseEntity.ok(modelMapperService.mapToList(volunteerService.getAllVolunteers(), VolunteerDTO.class));
    }

    // @PostMapping
    // public Volunteer createVolunteer(@RequestBody Volunteer volunteer) {
    //     return volunteerService.createVolunteer(volunteer);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerDTO> getVolunteerById(@PathVariable Long id) {
        return ResponseEntity.ok(modelMapperService.map(volunteerService.getVolunteerById(id), VolunteerDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolunteerDTO> updateVolunteer(@PathVariable Long id, @RequestBody Volunteer volunteerDetails) {
        // return volunteerService.updateVolunteer(id, volunteerDetails);
        return ResponseEntity.ok(modelMapperService.map(volunteerService.updateVolunteer(id, volunteerDetails), VolunteerDTO.class));
    }

    @DeleteMapping("/{id}")
    public void deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
    }
}
