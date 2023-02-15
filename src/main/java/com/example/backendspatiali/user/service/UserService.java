package com.example.backendspatiali.user.service;

import com.example.backendspatiali.user.data.User;
import com.example.backendspatiali.user.data.UserInfoResponse;
import com.example.backendspatiali.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserInfoResponse getUserInfo(String username){
        var user = userRepository.findByUsername(username).orElseThrow() ;
        return  UserInfoResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .role(String.valueOf(user.getRole()))
                .build();
    }
}
