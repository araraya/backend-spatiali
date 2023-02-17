package com.example.backendspatiali.refreshToken.repository;

import com.example.backendspatiali.refreshToken.data.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken>  findByUsername(String username);
}
