package com.example.backendspatiali.email.controller;


import com.example.backendspatiali.email.model.ActivationToken;
import com.example.backendspatiali.email.service.ActivationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class UserActivationController {
    @Autowired
    ActivationTokenService activationTokenService;

    @RequestMapping(value="/activate-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String activateUser(@RequestParam("email")String email) {
        var activation = activationTokenService.activateUser(email);

        if(activation){
            return "account-activated";
        } else {
            return "Cannot activate account";
        }
    }
}
