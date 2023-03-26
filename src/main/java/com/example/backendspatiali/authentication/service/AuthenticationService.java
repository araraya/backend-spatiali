package com.example.backendspatiali.authentication.service;


import com.example.backendspatiali.authentication.data.AuthenticationResponse;
import com.example.backendspatiali.authentication.data.LoginRequest;
import com.example.backendspatiali.authentication.data.RegisterRequest;
import com.example.backendspatiali.config.JwtService;
import com.example.backendspatiali.email.model.ActivationToken;
import com.example.backendspatiali.email.repository.ActivationTokenRepository;
import com.example.backendspatiali.email.service.ActivationTokenService;
import com.example.backendspatiali.email.service.EmailService;
import com.example.backendspatiali.refreshToken.repository.RefreshTokenRepository;
import com.example.backendspatiali.refreshToken.service.RefreshTokenService;
import com.example.backendspatiali.user.data.Role;
import com.example.backendspatiali.user.data.User;
import com.example.backendspatiali.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private  final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final EmailService emailService;
    private final ActivationTokenService activationTokenService;



    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .activate(false)
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        refreshTokenService.generateNewRefreshToken(user.getUsername());
        activationTokenService.generateActivationToken(user.getEmail());
        emailService.sendMail(request.getEmail());

        return AuthenticationResponse.builder()
                .status("Register Success")
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .activate(false)
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        refreshTokenService.generateNewRefreshToken(user.getUsername());
        activationTokenService.generateActivationToken(user.getEmail());
        emailService.sendMail(request.getEmail());

        return AuthenticationResponse.builder()
                .status("Register Admin Success")
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        boolean checkRefreshToken = refreshTokenService.gotRefreshToken(request.getUsername());
        System.out.println(checkRefreshToken);
        if(!checkRefreshToken){
            refreshTokenService.generateNewRefreshToken(user.getUsername());
        }

        return AuthenticationResponse.builder()
                .status("Login success")
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse generateNewToken(LoginRequest request) {
        boolean checkRefreshToken = refreshTokenService.gotRefreshToken(request.getUsername());
        if(checkRefreshToken){
            var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .status("Renew JWT success")
                    .token(jwtToken)
                    .build();
        }
        else{
            return AuthenticationResponse.builder()
                    .status("Failed to generate new JWT")
                    .build();
        }
    }

    public void logout(String username) {
        var userRefreshToken = refreshTokenRepository.findByUsername(username);
        if(userRefreshToken.isPresent()){
            refreshTokenService.deleteRefreshToken(username);
        }

    }
}
