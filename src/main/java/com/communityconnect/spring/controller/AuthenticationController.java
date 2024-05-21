package com.communityconnect.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communityconnect.spring.payload.request.AuthenticationRequest;
import com.communityconnect.spring.payload.request.RegisterRequest;
import com.communityconnect.spring.payload.response.AuthenticationResponse;
import com.communityconnect.spring.payload.response.DemoDTO;
import com.communityconnect.spring.service.AuthenticationService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<Object> register(
      @RequestBody RegisterRequest request) {
    try {
      AuthenticationResponse response = service.register(request);
      return ResponseEntity.ok(response);
    } catch (DuplicateKeyException e) {
      throw new DuplicateKeyException(e.getMessage());
    }   
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

  @GetMapping("/test")
  public DemoDTO test(){
    DemoDTO demoDTO = DemoDTO.builder().hello("hello from lambda").build();
    return demoDTO;
  }

}
