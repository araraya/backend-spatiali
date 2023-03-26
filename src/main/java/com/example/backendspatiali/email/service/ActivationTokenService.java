package com.example.backendspatiali.email.service;


import com.example.backendspatiali.email.model.ActivationToken;
import com.example.backendspatiali.email.repository.ActivationTokenRepository;
import com.example.backendspatiali.user.data.User;
import com.example.backendspatiali.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivationTokenService {

   private final ActivationTokenRepository activationTokenRepository;
   private final UserRepository userRepository;

    public void generateActivationToken(String email){
        var token = ActivationToken.builder()
                .email(email)
                .activationToken(UUID.randomUUID())
                .build();
        activationTokenRepository.save(token);
    }


    public boolean activateUser(String email){
        var token = activationTokenRepository.findByEmail(email);
        if(token != null){
            User activatingUser = userRepository.findByEmail(email);

            if(activatingUser != null){
                activatingUser.setActivate(true);
                userRepository.save(activatingUser);
                activationTokenRepository.deleteById(token.getTokenId());
                return true;
            } else {
                return false;
            }
        } else {
          return false;
        }
    }
}
