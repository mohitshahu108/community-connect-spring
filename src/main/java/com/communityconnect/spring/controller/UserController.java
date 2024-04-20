package com.communityconnect.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communityconnect.spring.model.User;
import com.communityconnect.spring.payload.request.ChangePasswordRequest;
import com.communityconnect.spring.payload.response.UserDTO;
import com.communityconnect.spring.service.ModelMapperService;
import com.communityconnect.spring.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final ModelMapperService modelMapperService;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/current")
    public ResponseEntity<UserDTO> getCurrentUser(Principal connectedUser) {
        var user = service.getUser(connectedUser);

        return ResponseEntity.ok(modelMapperService.map(user, UserDTO.class));
    }

}
