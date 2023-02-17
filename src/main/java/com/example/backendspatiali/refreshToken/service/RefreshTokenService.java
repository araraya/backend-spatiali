package com.example.backendspatiali.refreshToken.service;


import com.example.backendspatiali.refreshToken.data.RefreshToken;
import com.example.backendspatiali.refreshToken.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    public void generateNewRefreshToken(String username){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsername(username);
        refreshTokenRepository.save(refreshToken);
    }

    public Boolean gotRefreshToken(String username){
        RefreshToken refreshToken = refreshTokenRepository.findByUsername(username).orElseThrow();
        if(refreshToken != null){
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
