package com.communityconnect.spring.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ModelMapperService {
    @Autowired
    private final ModelMapper modelMapper;

    public <S, T> T map(S source, Class<T> targetType) {
        return modelMapper.map(source, targetType);
    }

    
    public <S, T> List<T> mapToList(List<S> sources, Class<T> targetType) {
        return sources.stream()
                .map(source -> map(source, targetType))
                .collect(Collectors.toList());
    }
}
