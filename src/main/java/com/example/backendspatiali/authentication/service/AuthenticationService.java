package com.example.backendspatiali.authentication.service;


import com.example.backendspatiali.authentication.data.AuthenticationResponse;
import com.example.backendspatiali.authentication.data.LoginRequest;
import com.example.backendspatiali.authentication.data.RegisterRequest;
import com.example.backendspatiali.config.JwtService;
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


    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        refreshTokenService.generateNewRefreshToken(user.getUsername());

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
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        refreshTokenService.generateNewRefreshToken(user.getUsername());

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

        return AuthenticationResponse.builder()
                .status("Login success")
                .token(jwtToken)
                .build();
    }

}
