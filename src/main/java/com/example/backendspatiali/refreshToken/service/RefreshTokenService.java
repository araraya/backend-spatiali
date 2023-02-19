package com.example.backendspatiali.refreshToken.service;


import com.example.backendspatiali.refreshToken.data.RefreshToken;
import com.example.backendspatiali.refreshToken.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    public void generateNewRefreshToken(String username){
        var token =  RefreshToken.builder()
                        .refreshToken(UUID.randomUUID())
                        .username(username)
                                .build();
        refreshTokenRepository.save(token);
    }

    public Boolean gotRefreshToken(String username){
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUsername(username);
        System.out.println(refreshToken);
        if(refreshToken.isPresent()){
            return true;
        } else {
           return false;
        }
    }

    public void deleteRefreshToken(String username){
        RefreshToken token = refreshTokenRepository.findByUsername(username).orElseThrow();
        refreshTokenRepository.deleteById(token.getId());
    }
}
