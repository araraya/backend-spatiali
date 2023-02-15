package com.example.backendspatiali.user.controller;

import com.example.backendspatiali.user.data.User;
import com.example.backendspatiali.user.data.UserInfoResponse;
import com.example.backendspatiali.user.repository.UserRepository;
import com.example.backendspatiali.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/GetUserInfo/{userId}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable("userId") UUID userId){
    return ResponseEntity.ok(userService.getUserInfo(userId));
    }
}
