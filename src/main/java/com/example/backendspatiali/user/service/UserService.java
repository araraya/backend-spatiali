package com.example.backendspatiali.user.service;

import com.example.backendspatiali.user.data.UserInfoResponse;
import com.example.backendspatiali.user.repository.UserRepository;
import org.geolatte.geom.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserInfoResponse getUserInfo(UUID userId){
        var user = userRepository.findById(userId).orElseThrow();

        return UserInfoResponse.builder()
                .username(user.getUsername())
                .spatial_data(user.getSpatial_datas())
                .build();
    }
}
