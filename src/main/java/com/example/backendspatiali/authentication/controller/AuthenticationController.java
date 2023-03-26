package com.example.backendspatiali.authentication.controller;


import com.example.backendspatiali.authentication.data.AuthenticationResponse;
import com.example.backendspatiali.authentication.data.LoginRequest;
import com.example.backendspatiali.authentication.data.RegisterRequest;
import com.example.backendspatiali.authentication.service.AuthenticationService;
import com.example.backendspatiali.user.data.User;
import com.example.backendspatiali.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ){
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if(user.isEmpty()){
            return ResponseEntity.ok(authenticationService.register(request));
        } else {
            return ResponseEntity.ok("Error, User already exist");
        }

    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(
            @RequestBody RegisterRequest request
    ){
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if(user.isEmpty()){
            return ResponseEntity.ok(authenticationService.registerAdmin(request));
        } else {
            return ResponseEntity.ok("Error, Username already exist");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest request
    ){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/generateNewToken")
    public ResponseEntity<AuthenticationResponse> generateNewToken(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.generateNewToken(request));
    }

    @PostMapping("/logout/{username}")
    public void logout(@PathVariable("username") String username){
        authenticationService.logout(username);
    }
}
