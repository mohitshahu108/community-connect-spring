package com.communityconnect.spring.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.communityconnect.spring.model.Volunteer;
import com.communityconnect.spring.payload.response.VolunteerDTO;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ModelMapperService {
    @Autowired
    private final ModelMapper modelMapper;

    public VolunteerDTO convertToDTO(Volunteer volunteer) {
        return modelMapper.map(volunteer, VolunteerDTO.class);
    }

    public List<VolunteerDTO> convertToDTOList(List<Volunteer> volunteers) {
        return volunteers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
