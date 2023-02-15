package com.example.backendspatiali.authentication.controller;


import com.example.backendspatiali.authentication.data.AuthenticationResponse;
import com.example.backendspatiali.authentication.data.LoginRequest;
import com.example.backendspatiali.authentication.data.RegisterRequest;
import com.example.backendspatiali.authentication.service.AuthenticationService;
import com.example.backendspatiali.user.data.User;
import com.example.backendspatiali.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if(user.toString() == "Optional.empty"){
            return ResponseEntity.ok(authenticationService.register(request));
        } else {
            final AuthenticationResponse response = AuthenticationResponse.builder()
                    .status("Error, User already exist")
                    .token("null")
                    .build();
            return ResponseEntity.ok(response);
        }

    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ){
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if(user.toString() == "Optional.empty"){
            return ResponseEntity.ok(authenticationService.registerAdmin(request));
        } else {
            final AuthenticationResponse response = AuthenticationResponse.builder()
                    .status("Error, Username already exist")
                    .token("null")
                    .build();
            return ResponseEntity.ok(response);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest request
    ){
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
