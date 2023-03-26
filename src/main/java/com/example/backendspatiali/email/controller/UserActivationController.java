package com.example.backendspatiali.email.controller;


import com.example.backendspatiali.email.model.ActivationToken;
import com.example.backendspatiali.email.service.ActivationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserActivationController {
    @Autowired
    ActivationTokenService activationTokenService;

    @RequestMapping(value="/activate-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> activateUser(@RequestParam("email")String email) {
        return activationTokenService.activateUser(email);
    }
}
